-- Users
INSERT INTO users (id, name, email, phone_number) VALUES (1, 'John Doe', 'john.doe@gilasw.com', '1234567890');
INSERT INTO users (id, name, email, phone_number) VALUES (2, 'Jane Smith', 'jane.smith@gilasw.com', '0987654321');
INSERT INTO users (id, name, email, phone_number) VALUES (3, 'Carlos García', 'carlos.garcia@gilasw.com', '1122334455');
INSERT INTO users (id, name, email, phone_number) VALUES (4, 'María Rodríguez', 'maria.rodriguez@gilasw.com', '2233445566');
INSERT INTO users (id, name, email, phone_number) VALUES (5, 'Michael Johnson', 'michael.johnson@gilasw.com', '3344556677');
INSERT INTO users (id, name, email, phone_number) VALUES (6, 'Lucía Martínez', 'lucia.martinez@gilasw.com', '4455667788');
INSERT INTO users (id, name, email, phone_number) VALUES (7, 'David Brown', 'david.brown@gilasw.com', '5566778899');
INSERT INTO users (id, name, email, phone_number) VALUES (8, 'Isabel Hernández', 'isabel.hernandez@gilasw.com', '6677889900');
INSERT INTO users (id, name, email, phone_number) VALUES (9, 'William Jones', 'william.jones@gilasw.com', '7788990011');
INSERT INTO users (id, name, email, phone_number) VALUES (10, 'Ana López', 'ana.lopez@gilasw.com', '8899001122');
INSERT INTO users (id, name, email, phone_number) VALUES (11, 'James Williams', 'james.williams@gilasw.com', '9900112233');
INSERT INTO users (id, name, email, phone_number) VALUES (12, 'Jorge Sánchez', 'jorge.sanchez@gilasw.com', '1011121314');
INSERT INTO users (id, name, email, phone_number) VALUES (13, 'Henry Wilson', 'henry.wilson@gilasw.com', '1213141516');
INSERT INTO users (id, name, email, phone_number) VALUES (14, 'Laura Fernández', 'laura.fernandez@gilasw.com', '1314151617');
INSERT INTO users (id, name, email, phone_number) VALUES (15, 'George Taylor', 'george.taylor@gilasw.com', '1415161718');
INSERT INTO users (id, name, email, phone_number) VALUES (16, 'Elena Gómez', 'elena.gomez@gilasw.com', '1516171819');
INSERT INTO users (id, name, email, phone_number) VALUES (17, 'Richard Davis', 'richard.davis@gilasw.com', '1617181920');
INSERT INTO users (id, name, email, phone_number) VALUES (18, 'Marta Ruiz', 'marta.ruiz@gilasw.com', '1718192021');
INSERT INTO users (id, name, email, phone_number) VALUES (19, 'Charles Moore', 'charles.moore@gilasw.com', '1819202122');
INSERT INTO users (id, name, email, phone_number) VALUES (20, 'Sofía Pérez', 'sofia.perez@gilasw.com', '1920212223');

-- User's Subs
INSERT INTO user_subscriptions (user_id, category) VALUES (1, 'Sports');
INSERT INTO user_subscriptions (user_id, category) VALUES (1, 'Finance');
INSERT INTO user_subscriptions (user_id, category) VALUES (2, 'Movies');
INSERT INTO user_subscriptions (user_id, category) VALUES (3, 'Sports');
INSERT INTO user_subscriptions (user_id, category) VALUES (4, 'Movies');
INSERT INTO user_subscriptions (user_id, category) VALUES (5, 'Finance');
INSERT INTO user_subscriptions (user_id, category) VALUES (6, 'Sports');
INSERT INTO user_subscriptions (user_id, category) VALUES (7, 'Finance');
INSERT INTO user_subscriptions (user_id, category) VALUES (8, 'Movies');
INSERT INTO user_subscriptions (user_id, category) VALUES (9, 'Sports');
INSERT INTO user_subscriptions (user_id, category) VALUES (10, 'Finance');
INSERT INTO user_subscriptions (user_id, category) VALUES (11, 'Movies');
INSERT INTO user_subscriptions (user_id, category) VALUES (12, 'Sports');
INSERT INTO user_subscriptions (user_id, category) VALUES (13, 'Finance');
INSERT INTO user_subscriptions (user_id, category) VALUES (14, 'Movies');
INSERT INTO user_subscriptions (user_id, category) VALUES (15, 'Sports');
INSERT INTO user_subscriptions (user_id, category) VALUES (16, 'Finance');
INSERT INTO user_subscriptions (user_id, category) VALUES (17, 'Movies');
INSERT INTO user_subscriptions (user_id, category) VALUES (18, 'Sports');
INSERT INTO user_subscriptions (user_id, category) VALUES (19, 'Finance');
INSERT INTO user_subscriptions (user_id, category) VALUES (20, 'Movies');

-- User's channels
INSERT INTO user_channels (user_id, channel) VALUES (1, 'EMAIL');
INSERT INTO user_channels (user_id, channel) VALUES (1, 'SMS');
INSERT INTO user_channels (user_id, channel) VALUES (2, 'EMAIL');
INSERT INTO user_channels (user_id, channel) VALUES (2, 'PUSH_NOTIFICATION');
INSERT INTO user_channels (user_id, channel) VALUES (3, 'SMS');
INSERT INTO user_channels (user_id, channel) VALUES (3, 'PUSH_NOTIFICATION');
INSERT INTO user_channels (user_id, channel) VALUES (4, 'EMAIL');
INSERT INTO user_channels (user_id, channel) VALUES (4, 'SMS');
INSERT INTO user_channels (user_id, channel) VALUES (5, 'PUSH_NOTIFICATION');
INSERT INTO user_channels (user_id, channel) VALUES (6, 'EMAIL');
INSERT INTO user_channels (user_id, channel) VALUES (6, 'SMS');
INSERT INTO user_channels (user_id, channel) VALUES (7, 'PUSH_NOTIFICATION');
INSERT INTO user_channels (user_id, channel) VALUES (8, 'EMAIL');
INSERT INTO user_channels (user_id, channel) VALUES (8, 'PUSH_NOTIFICATION');
INSERT INTO user_channels (user_id, channel) VALUES (9, 'SMS');
INSERT INTO user_channels (user_id, channel) VALUES (10, 'EMAIL');
INSERT INTO user_channels (user_id, channel) VALUES (10, 'PUSH_NOTIFICATION');
INSERT INTO user_channels (user_id, channel) VALUES (11, 'SMS');
INSERT INTO user_channels (user_id, channel) VALUES (12, 'EMAIL');
INSERT INTO user_channels (user_id, channel) VALUES (12, 'SMS');
INSERT INTO user_channels (user_id, channel) VALUES (13, 'PUSH_NOTIFICATION');
INSERT INTO user_channels (user_id, channel) VALUES (14, 'EMAIL');
INSERT INTO user_channels (user_id, channel) VALUES (14, 'PUSH_NOTIFICATION');
INSERT INTO user_channels (user_id, channel) VALUES (15, 'SMS');
INSERT INTO user_channels (user_id, channel) VALUES (16, 'EMAIL');
INSERT INTO user_channels (user_id, channel) VALUES (16, 'PUSH_NOTIFICATION');
INSERT INTO user_channels (user_id, channel) VALUES (17, 'SMS');
INSERT INTO user_channels (user_id, channel) VALUES (18, 'EMAIL');
INSERT INTO user_channels (user_id, channel) VALUES (18, 'SMS');
INSERT INTO user_channels (user_id, channel) VALUES (19, 'PUSH_NOTIFICATION');
INSERT INTO user_channels (user_id, channel) VALUES (20, 'EMAIL');
INSERT INTO user_channels (user_id, channel) VALUES (20, 'SMS');
