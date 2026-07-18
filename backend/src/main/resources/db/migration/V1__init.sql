-- ─── Admin Role Table ────────────────────────────────────────────────────────
CREATE TABLE admin_role (
    id SERIAL PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL
);

INSERT INTO admin_role (id, role_name) VALUES
(1, 'Level 1 Access - Super Admin'),
(2, 'Level 2 Access - Admin Lead'),
(3, 'Level 3 Access - Standard / Support Admin');

-- ─── Admin Table ─────────────────────────────────────────────────────────────
CREATE TABLE admin (
    id SERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    fullname VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(15) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'active',
    create_token VARCHAR(6) NOT NULL DEFAULT '',
    reset_token VARCHAR(255) DEFAULT NULL,
    role_id INTEGER NOT NULL REFERENCES admin_role(id) ON DELETE CASCADE
);

INSERT INTO admin (id, username, password, fullname, email, phone, status, create_token, reset_token, role_id) VALUES
(8, 'adminlead', '$2y$10$Q/dB7LTeF2hQulfqTkHnLOM.8N//EsIAj5JlxLg2eGpjfG4PaIgxS', 'adminlead', 'torresailene1@gmail.com', '0955988097', 'active', '', NULL, 2),
(10, 'superadm', '$2y$10$ZY8N0u9CG6sjDPg/oStyCecaAem9RPMogL7y0.YgFOKLleLCEDYZq', 'Superadmin', 'blaireashb@gmail.com', '0987654321', 'active', '', NULL, 1),
(11, 'suppad', '$2y$10$pOR631O52mzJ0zM43UG5xu.OzJfoNRXkDhqpWCFZkYoqwpntk0XRu', 'Support Admin', 'ashyrieheisen@gmail.com', '0987654321', 'active', '', NULL, 3),
(12, 'adlead', '$2y$10$yo0zRKhi2t5w/pWqXRbosOyytwsz8E5aMzOSIKDQq6wJ00HZ5cgOq', 'fsf', 'adlead@gmail.com', '0987654321', 'active', '', NULL, 2),
(13, 'supad', '$2y$10$bvuPhwrEcIhMPh.khT5aZuisjTyG0UzTKsfMx6CCDVB/h0o5.fDvu', 'adasda', 'supad@gmail.com', '09876543', 'active', '', NULL, 3),
(14, 'a2', '$2y$10$XNqmAJm/zUaraEgYNtcGhea8ZCv2wST6ox0WaPf2cwr2BKGnL4kKW', 'a2', 'ailenetorres834@gmail.com', '0955988097', 'active', '', NULL, 1);

-- ─── Admin 2FA Table ─────────────────────────────────────────────────────────
CREATE TABLE admin_2fa (
    id SERIAL PRIMARY KEY,
    admin_id INTEGER NOT NULL REFERENCES admin(id) ON DELETE CASCADE,
    otp_secret VARCHAR(255) NOT NULL,
    is_2fa_enabled BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

INSERT INTO admin_2fa (id, admin_id, otp_secret, is_2fa_enabled, created_at, updated_at) VALUES
(3, 8, '6Q32LHD5N6BCWCG2RJ2LWEHK5YC5PPSQ', TRUE, NOW(), NOW()),
(5, 10, 'PWDPLW52L5AUEOH2RVOCJMV33Y7ONRH3', TRUE, NOW(), NOW()),
(6, 11, 'A6CFPVT4MV4DT3UCH24F3UANSMQVBA77', FALSE, NOW(), NOW()),
(7, 12, 'RRBLZMRR3OYPZ7NVU6D7FYJ4T4IPZO7P', TRUE, NOW(), NOW()),
(8, 13, 'IDNPENZENIYAUM2QRBROBGREOSCCBTNF', TRUE, NOW(), NOW()),
(9, 14, 'WG6EQEBWV4PGDJ4AS7LU6EVQ7FSOJKJT', FALSE, NOW(), NOW());

-- ─── Contacts Table ──────────────────────────────────────────────────────────
CREATE TABLE contacts (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email VARCHAR(255) NOT NULL,
    subject TEXT NOT NULL DEFAULT 'none',
    message TEXT NOT NULL,
    created_c TIMESTAMP NOT NULL DEFAULT NOW()
);

-- ─── Currencies Table ────────────────────────────────────────────────────────
CREATE TABLE currencies (
    id SERIAL PRIMARY KEY,
    currency VARCHAR(20) NOT NULL UNIQUE,
    symbol VARCHAR(10)
);

INSERT INTO currencies (id, currency, symbol) VALUES
(1, 'PHP', '₱'),
(2, 'USD', '$');

-- ─── Customers Table ─────────────────────────────────────────────────────────
CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    name_customer VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    email_customer VARCHAR(255) NOT NULL,
    phone_customer VARCHAR(15) NOT NULL,
    address TEXT NOT NULL,
    password VARCHAR(255) NOT NULL,
    verification_code VARCHAR(6) DEFAULT NULL,
    is_verified BOOLEAN DEFAULT FALSE,
    note_customer TEXT DEFAULT NULL,
    reset_token VARCHAR(255) DEFAULT NULL,
    date_at TIMESTAMP NOT NULL DEFAULT NOW()
);

INSERT INTO customers (id, name_customer, username, email_customer, phone_customer, address, password, verification_code, is_verified, note_customer, reset_token, date_at) VALUES
(1, 'Juan Dela Cruz', 'juandc', 'juan@example.com', '9123456789', '123 Main St, Manila', '$2y$10$somehashedpassword1', NULL, TRUE, NULL, NULL, NOW()),
(2, 'Maria Santos', 'mariasantos', 'maria@example.com', '9234567890', '456 Park Ave, Quezon City', '$2y$10$somehashedpassword2', NULL, TRUE, NULL, NULL, NOW()),
(3, 'Pedro Reyes', 'pedroreyes', 'pedro@example.com', '9345678901', '789 Beach Rd, Cebu', '$2y$10$somehashedpassword3', NULL, TRUE, NULL, NULL, NOW()),
(16, 'Ailene Torres', 'ai', 'ailenetorres834@gmail.com', '0955988097', '1255 Makati City', '$2y$10$TgrSMG7SiXtEhpeDA/l9V.JgobtxZbVYKs.cUqm/ezC5VlOKvrXFC', NULL, TRUE, NULL, NULL, NOW()),
(17, 'Gian Bernardino', 'gb', 'atorres.a12241700@umak.edu.ph', '09876543', 'Baguio City', '$2y$10$KdmmGOyowA0utn43/NaijuCaGEsZ6vDV0PK5evnoYMbGh54Y5gqxm', NULL, FALSE, NULL, NULL, NOW());

-- ─── Customer 2FA Table ──────────────────────────────────────────────────────
CREATE TABLE customer_2fa (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL REFERENCES customers(id) ON DELETE CASCADE,
    otp_secret VARCHAR(255) NOT NULL,
    is_2fa_enabled BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

INSERT INTO customer_2fa (id, customer_id, otp_secret, is_2fa_enabled, created_at, updated_at) VALUES
(1, 17, 'I2VBEI6YXF5UM6FUXGZ2GTUXR44NVBG7', TRUE, NOW(), NOW());

-- ─── Customer Companies Table ────────────────────────────────────────────────
CREATE TABLE customer_companies (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL REFERENCES customers(id) ON DELETE CASCADE,
    company_name VARCHAR(255) NOT NULL,
    company_address TEXT NOT NULL,
    job_title VARCHAR(100) NOT NULL,
    business_document VARCHAR(255) DEFAULT NULL,
    business_id VARCHAR(255) DEFAULT NULL
);

INSERT INTO customer_companies (id, customer_id, company_name, company_address, job_title, business_document, business_id) VALUES
(13, 16, 'PBL Co.', 'J.P. Rizal Makati', 'Manager', 'admin/customerfileupload/67444a0101064.pdf', 'customeridupload/PIC WITH BG OF CCIS LOGO.jpg'),
(14, 17, 'COJ', 'Ayala', 'Staff', 'admin/customerfileupload/67472f559f93e.pdf', 'admin/customeridupload/67472f60e644c.png');

-- ─── Products Table ──────────────────────────────────────────────────────────
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name_product TEXT NOT NULL,
    description_product TEXT NOT NULL,
    price_product DECIMAL(10,2) NOT NULL,
    currency VARCHAR(20) NOT NULL REFERENCES currencies(currency),
    img_product VARCHAR(255) NOT NULL,
    stock_product INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    category VARCHAR(50) NOT NULL DEFAULT 'OTHERS'
);

INSERT INTO products (id, name_product, description_product, price_product, currency, img_product, stock_product, created_at, category) VALUES
(13, 'BOOM BARRIER WITH ARM', 'Electromechanical Barrier with Arm', 339.00, 'USD', '674c844ebce3a.png', 20, NOW(), 'PARKING EQUIPMENT'),
(14, 'MEMBER TERMINAL', 'Member Terminal system', 245.99, 'USD', '674c70edeb5d5.png', 20, NOW(), 'PARKING EQUIPMENT'),
(15, 'SHORT RANGE READER', 'Short Range RFID Reader', 75.00, 'USD', '674c71ba227b6.png', 20, NOW(), 'RFID READER'),
(16, 'SERVER TERMINAL', 'Core Server Terminal', 3495.00, 'USD', '674c73b46038a.png', 20, NOW(), 'OTHERS'),
(17, 'AUTOPAY STATION TERMINAL', 'Autopay terminal kiosk', 7500.00, 'USD', '674c7f0944db1.png', 20, NOW(), 'PARKING EQUIPMENT'),
(18, 'POS TERMINAL', 'Point of sale terminal', 297.49, 'USD', '674c86bbf3413.png', 20, NOW(), 'PARKING EQUIPMENT'),
(19, 'EXIT KIOSK TERMINAL', 'Exit kiosk terminal', 499.00, 'USD', '674c875bae312.png', 20, NOW(), 'PARKING EQUIPMENT'),
(20, 'ENTRANCE KIOSK TERMINAL', 'Entrance kiosk terminal', 499.00, 'USD', '674c880928bb4.png', 20, NOW(), 'PARKING EQUIPMENT'),
(21, 'MID RANGE READER', 'Mid range RFID reader', 99.00, 'USD', '674d4083a8fc8.png', 20, NOW(), 'RFID READER'),
(22, 'LONG RANGE READER', 'Long range RFID reader', 200.00, 'USD', '674d411c4df04.png', 20, NOW(), 'RFID READER'),
(23, 'SECURITY CAMERA POLE', 'Camera and pole security setup', 945.00, 'USD', '674d42448b3ac.png', 20, NOW(), 'PARKING EQUIPMENT'),
(24, 'PORTABLE DATA TERMINAL W/ BT PRINTER', 'Portable android terminal', 159.00, 'USD', '674d72e7e34d7.PNG', 20, NOW(), 'OTHERS');

-- ─── Status Table ────────────────────────────────────────────────────────────
CREATE TABLE status (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

INSERT INTO status (id, name) VALUES
(1, 'pending'),
(2, 'cancelled'),
(3, 'processing'),
(4, 'pending payment'),
(5, 'completed'),
(6, 'failed');

-- ─── Orders Table ────────────────────────────────────────────────────────────
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    orders_number VARCHAR(20) NOT NULL,
    customer_id INTEGER NOT NULL REFERENCES customers(id) ON DELETE CASCADE,
    product_name TEXT NOT NULL,
    product_quantity VARCHAR(20) NOT NULL,
    product_price DECIMAL(10,2) NOT NULL,
    currency VARCHAR(20) NOT NULL REFERENCES currencies(currency),
    subtotal VARCHAR(20) NOT NULL,
    note_customer TEXT NOT NULL,
    order_date TIMESTAMP NOT NULL DEFAULT NOW(),
    order_status VARCHAR(20) NOT NULL DEFAULT 'pending payment'
);

INSERT INTO orders (id, orders_number, customer_id, product_name, product_quantity, product_price, currency, subtotal, note_customer, order_date, order_status) VALUES
(1, '#23380', 1, 'Parking Sensor Kit - 4 Sensors', '2', 49.99, 'PHP', '99.98', 'Juan Dela Cruz', NOW(), 'completed'),
(2, '#68817', 2, 'Wireless Parking Sensor System', '1', 89.99, 'PHP', '89.99', 'Maria Santos', NOW(), 'completed'),
(3, '#18661', 3, 'Parking Assist Camera', '3', 39.99, 'PHP', '119.97', 'Rush order', NOW(), 'completed'),
(4, '#49459', 16, 'Wireless Parking Sensor System', '2', 89.99, 'USD', '179.98', 'gfgfgfg', NOW(), 'completed'),
(5, '#75666', 16, 'Parking Sensor Kit - 4 Sensors', '6', 49.99, 'USD', '299.94', 'fsdfsddff', NOW(), 'completed'),
(6, '#75666', 16, 'Wireless Parking Sensor System', '3', 89.99, 'USD', '269.97', 'fsdfsddff', NOW(), 'completed'),
(7, '#82668', 17, 'Parking Sensor Kit - 4 Sensors', '1', 49.99, 'PHP', '49.99', 'fwefwefwefwef', NOW(), 'pending payment');

-- ─── Support Tickets Table ───────────────────────────────────────────────────
CREATE TABLE support_tickets (
    ticket_id VARCHAR(20) PRIMARY KEY,
    customer_id INTEGER NOT NULL REFERENCES customers(id) ON DELETE CASCADE,
    product_id INTEGER NOT NULL REFERENCES products(id),
    resolved VARCHAR(5) NOT NULL DEFAULT 'no',
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

INSERT INTO support_tickets (ticket_id, customer_id, product_id, resolved, created_at) VALUES
('TICKET-1992859776900', 17, 19, 'no', NOW());

-- ─── Messages Table ──────────────────────────────────────────────────────────
CREATE TABLE messages (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES customers(id) ON DELETE CASCADE,
    admin_id INTEGER REFERENCES admin(id),
    product_id INTEGER REFERENCES products(id),
    ticket_id VARCHAR(20) NOT NULL REFERENCES support_tickets(ticket_id) ON DELETE CASCADE,
    message TEXT,
    sender_type VARCHAR(10) NOT NULL,
    sender_id INTEGER NOT NULL DEFAULT 0,
    timestamp TIMESTAMP NOT NULL DEFAULT NOW()
);

INSERT INTO messages (id, customer_id, admin_id, product_id, ticket_id, message, sender_type, sender_id, timestamp) VALUES
(8, 17, NULL, 19, 'TICKET-1992859776900', 'Inquiry started.', 'customer', 17, NOW());

-- ─── Deployments Table ────────────────────────────────────────────────────────
CREATE TABLE deployments (
    id SERIAL PRIMARY KEY,
    order_id INTEGER NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    deployment_leader_id INTEGER NOT NULL REFERENCES admin(id) ON DELETE CASCADE,
    creation_time TIMESTAMP NOT NULL DEFAULT NOW(),
    status VARCHAR(20) NOT NULL DEFAULT 'pending'
);

INSERT INTO deployments (id, order_id, name, location, deployment_leader_id, creation_time, status) VALUES
(1, 4, 'dfsdf', 'fsdf', 8, NOW(), 'pending'),
(2, 5, 'hdh', 'h', 8, NOW(), 'pending'),
(3, 5, 'fdsf', 'Makati City', 12, NOW(), 'pending');

-- ─── Deployment Logs Table ───────────────────────────────────────────────────
CREATE TABLE deployment_logs (
    id SERIAL PRIMARY KEY,
    deployment_id INTEGER NOT NULL REFERENCES deployments(id),
    status VARCHAR(20) NOT NULL DEFAULT 'completed',
    deployment_date TIMESTAMP NOT NULL DEFAULT NOW(),
    order_id INTEGER NOT NULL REFERENCES orders(id),
    customer_id INTEGER NOT NULL REFERENCES customers(id) ON DELETE CASCADE,
    completed_at TIMESTAMP NOT NULL DEFAULT NOW(),
    deployment_name VARCHAR(255) NOT NULL,
    deployment_leader_id INTEGER NOT NULL REFERENCES admin(id),
    admin_id INTEGER NOT NULL REFERENCES admin(id)
);

-- ─── Deployment Team Members Table ───────────────────────────────────────────
CREATE TABLE deployment_team_members (
    id SERIAL PRIMARY KEY,
    deployment_id INTEGER NOT NULL REFERENCES deployments(id) ON DELETE CASCADE,
    admin_id INTEGER NOT NULL REFERENCES admin(id) ON DELETE CASCADE,
    UNIQUE (deployment_id, admin_id)
);

INSERT INTO deployment_team_members (id, deployment_id, admin_id) VALUES
(1, 1, 11);

-- ─── User Cart Table ─────────────────────────────────────────────────────────
CREATE TABLE user_cart (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES customers(id) ON DELETE CASCADE,
    product_id INTEGER REFERENCES products(id) ON DELETE CASCADE,
    quantity INTEGER,
    product_name VARCHAR(255) NOT NULL,
    product_price DECIMAL(10,2) NOT NULL,
    currency VARCHAR(10) NOT NULL
);

-- Set sequences to start after initial data values
SELECT setval('admin_role_id_seq', (SELECT MAX(id) FROM admin_role));
SELECT setval('admin_id_seq', (SELECT MAX(id) FROM admin));
SELECT setval('admin_2fa_id_seq', (SELECT MAX(id) FROM admin_2fa));
SELECT setval('currencies_id_seq', (SELECT MAX(id) FROM currencies));
SELECT setval('customers_id_seq', (SELECT MAX(id) FROM customers));
SELECT setval('customer_2fa_id_seq', (SELECT MAX(id) FROM customer_2fa));
SELECT setval('customer_companies_id_seq', (SELECT MAX(id) FROM customer_companies));
SELECT setval('products_id_seq', (SELECT MAX(id) FROM products));
SELECT setval('status_id_seq', (SELECT MAX(id) FROM status));
SELECT setval('orders_id_seq', (SELECT MAX(id) FROM orders));
SELECT setval('messages_id_seq', (SELECT MAX(id) FROM messages));
SELECT setval('deployments_id_seq', (SELECT MAX(id) FROM deployments));
SELECT setval('deployment_team_members_id_seq', (SELECT MAX(id) FROM deployment_team_members));
