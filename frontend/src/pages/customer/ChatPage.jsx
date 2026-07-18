import React, { useState, useEffect, useRef } from 'react';
import { useParams } from 'react-router-dom';
import axios from '../../api/axios';
import { useAuth } from '../../hooks/useAuth';

export default function ChatPage() {
  const { ticketId } = useParams();
  const { user } = useAuth();
  const [messages, setMessages] = useState([]);
  const [text, setText] = useState('');
  const chatBottomRef = useRef(null);

  const fetchMessages = () => {
    axios.get(`/tickets/${ticketId}/messages`)
      .then(res => setMessages(res.data))
      .catch(err => console.error(err));
  };

  useEffect(() => {
    fetchMessages();
    const interval = setInterval(fetchMessages, 5000);
    return () => clearInterval(interval);
  }, [ticketId]);

  useEffect(() => {
    chatBottomRef.current?.scrollIntoView({ behavior: 'smooth' });
  }, [messages]);

  const handleSend = async (e) => {
    e.preventDefault();
    if (!text.trim()) return;
    try {
      await axios.post(`/tickets/${ticketId}/messages`, { message: text });
      setText('');
      fetchMessages();
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div className="container" style={{ maxWidth: '800px' }}>
      <h2 style={{ color: '#280068' }}>Inquiry Chat: {ticketId}</h2>
      <div className="card" style={{ height: '500px', display: 'flex', flexDirection: 'column', padding: 0 }}>
        {/* Messages list */}
        <div style={{ flex: 1, overflowY: 'auto', padding: '1.5rem', display: 'flex', flexDirection: 'column', gap: '1rem' }}>
          {messages.map(msg => {
            const isMe = (msg.senderType === 'customer' && !user?.role?.includes('ADMIN')) || 
                         (msg.senderType === 'admin' && user?.role?.includes('ADMIN'));
            return (
              <div key={msg.id} style={{
                alignSelf: isMe ? 'flex-end' : 'flex-start',
                backgroundColor: isMe ? '#4a148c' : '#f0f0f5',
                color: isMe ? '#fff' : '#333',
                padding: '0.8rem 1.2rem',
                borderRadius: '15px',
                maxWidth: '70%',
                boxShadow: '0 2px 5px rgba(0,0,0,0.05)'
              }}>
                <div style={{ fontSize: '0.8rem', opacity: 0.8, marginBottom: '0.3rem' }}>
                  {msg.senderType === 'customer' ? 'Customer' : 'Support Team'}
                </div>
                <div>{msg.message}</div>
                <div style={{ fontSize: '0.7rem', opacity: 0.6, marginTop: '0.3rem', textAlign: 'right' }}>
                  {new Date(msg.timestamp).toLocaleTimeString()}
                </div>
              </div>
            );
          })}
          <div ref={chatBottomRef} />
        </div>

        {/* Input box */}
        <form onSubmit={handleSend} style={{ display: 'flex', borderTop: '1px solid #e5e5e5', padding: '1rem' }}>
          <input
            type="text"
            className="form-control"
            value={text}
            onChange={e => setText(e.target.value)}
            placeholder="Type your message here..."
            style={{ flex: 1, marginRight: '1rem' }}
          />
          <button type="submit" className="btn btn-primary">Send</button>
        </form>
      </div>
    </div>
  );
}
