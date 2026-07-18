import React from 'react';

export default function Footer() {
  return (
    <footer style={{
      backgroundColor: '#280068',
      color: '#fff',
      padding: '2rem 1rem',
      textAlign: 'center',
      marginTop: 'auto'
    }}>
      <div style={{ maxWidth: '1200px', margin: '0 auto', display: 'flex', flexWrap: 'wrap', justifyContent: 'space-between', gap: '2rem', paddingBottom: '2rem', borderBottom: '1px solid rgba(255,255,255,0.1)', textAlign: 'left' }}>
        <div>
          <h3>Deltech Parking Systems</h3>
          <p>State-of-the-art parking equipment and systems designed for efficiency, security, and convenience.</p>
        </div>
        <div>
          <h4>Links</h4>
          <ul style={{ listStyle: 'none', padding: 0 }}>
            <li><a href="/about">About Us</a></li>
            <li><a href="/contact">Contact Us</a></li>
            <li><a href="/">Products</a></li>
          </ul>
        </div>
        <div>
          <h4>Social Media</h4>
          <div style={{ display: 'flex', gap: '1rem', fontSize: '1.5rem' }}>
            <a href="https://facebook.com" target="_blank" rel="noreferrer"><i className="fab fa-facebook"></i></a>
            <a href="https://linkedin.com" target="_blank" rel="noreferrer"><i className="fab fa-linkedin"></i></a>
            <a href="https://twitter.com" target="_blank" rel="noreferrer"><i className="fab fa-twitter"></i></a>
            <a href="https://youtube.com" target="_blank" rel="noreferrer"><i className="fab fa-youtube"></i></a>
          </div>
        </div>
      </div>
      <p style={{ marginTop: '2rem', opacity: 0.6, fontSize: '0.9rem' }}>
        &copy; {new Date().getFullYear()} Deltech Parking Systems and Solutions Inc. All rights reserved.
      </p>
    </footer>
  );
}
