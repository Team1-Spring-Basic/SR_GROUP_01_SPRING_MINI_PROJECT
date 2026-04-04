INSERT INTO app_roles (role_id, name) VALUES
                                          ('a1b2c3d4-0001-0000-0000-000000000001', 'ROLE_USER'),
                                          ('a1b2c3d4-0001-0000-0000-000000000002', 'ROLE_ADMIN');

-- ─────────────────────────────────────────
-- Users (password = bcrypt of "password123")
-- ─────────────────────────────────────────
INSERT INTO app_users (app_user_id, username, email, password, level, xp, is_verified) VALUES
                                                                                                      ('550e8400-0001-0000-0000-000000000001', 'sokha_dev', 'sokha@example.com',    '$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36WQoeG6Lruj3vjPGga31lW', 3, 1200, TRUE),
                                                                                                      ('550e8400-0001-0000-0000-000000000002', 'dara_codes', 'dara@example.com',     '$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36WQoeG6Lruj3vjPGga31lW', 2, 450,  TRUE),
                                                                                                      ('550e8400-0001-0000-0000-000000000003', 'admin_youlong', 'youlong@example.com',  '$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36WQoeG6Lruj3vjPGga31lW', 5, 3800, TRUE),
                                                                                                      ('550e8400-0001-0000-0000-000000000004', 'channary_h', 'channary@example.com', '$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36WQoeG6Lruj3vjPGga31lW', 1, 80,   FALSE);


INSERT INTO app_user_roles (app_user_id, role_id) VALUES
                                                      ('550e8400-0001-0000-0000-000000000001', 'a1b2c3d4-0001-0000-0000-000000000001'), -- sokha    → ROLE_USER
                                                      ('550e8400-0001-0000-0000-000000000002', 'a1b2c3d4-0001-0000-0000-000000000001'), -- dara     → ROLE_USER
                                                      ('550e8400-0001-0000-0000-000000000003', 'a1b2c3d4-0001-0000-0000-000000000001'), -- youlong  → ROLE_USER
                                                      ('550e8400-0001-0000-0000-000000000003', 'a1b2c3d4-0001-0000-0000-000000000002'), -- youlong  → ROLE_ADMIN
                                                      ('550e8400-0001-0000-0000-000000000004', 'a1b2c3d4-0001-0000-0000-000000000001'); -- channary → ROLE_USER


INSERT INTO habits (habit_id, app_user_id, title, description, frequency, is_active) VALUES
                                                                                         ('b0000001-0000-0000-0000-000000000001', '550e8400-0001-0000-0000-000000000001', 'Morning Run',       'Run 5km every morning',          'daily',  TRUE),
                                                                                         ('b0000001-0000-0000-0000-000000000002', '550e8400-0001-0000-0000-000000000001', 'Read 30 Minutes',   'Read a book before bed',         'daily',  TRUE),
                                                                                         ('b0000001-0000-0000-0000-000000000003', '550e8400-0001-0000-0000-000000000001', 'Weekly Review',     'Review goals every Sunday',      'weekly', TRUE),
                                                                                         ('b0000001-0000-0000-0000-000000000004', '550e8400-0001-0000-0000-000000000002', 'Drink 2L Water',    'Stay hydrated throughout day',   'daily',  TRUE),
                                                                                         ('b0000001-0000-0000-0000-000000000005', '550e8400-0001-0000-0000-000000000002', 'Meditate',          '10 min mindfulness session',     'daily',  FALSE),
                                                                                         ('b0000001-0000-0000-0000-000000000006', '550e8400-0001-0000-0000-000000000003', 'LeetCode Practice', 'Solve at least 1 problem a day', 'daily',  TRUE),
                                                                                         ('b0000001-0000-0000-0000-000000000007', '550e8400-0001-0000-0000-000000000004', 'Stretch',           'Morning stretching routine',     'daily',  TRUE);


INSERT INTO habit_logs (habit_log_id, habit_id, log_date, status, xp_earned) VALUES
                                                                                 ('c0000001-0000-0000-0000-000000000001', 'b0000001-0000-0000-0000-000000000001', CURRENT_DATE - 6, 'completed', 20),
                                                                                 ('c0000001-0000-0000-0000-000000000002', 'b0000001-0000-0000-0000-000000000001', CURRENT_DATE - 5, 'completed', 20),
                                                                                 ('c0000001-0000-0000-0000-000000000003', 'b0000001-0000-0000-0000-000000000001', CURRENT_DATE - 4, 'missed',     0),
                                                                                 ('c0000001-0000-0000-0000-000000000004', 'b0000001-0000-0000-0000-000000000001', CURRENT_DATE - 3, 'completed', 20),
                                                                                 ('c0000001-0000-0000-0000-000000000005', 'b0000001-0000-0000-0000-000000000001', CURRENT_DATE - 2, 'completed', 20),
                                                                                 ('c0000001-0000-0000-0000-000000000006', 'b0000001-0000-0000-0000-000000000001', CURRENT_DATE - 1, 'completed', 20),
                                                                                 ('c0000001-0000-0000-0000-000000000007', 'b0000001-0000-0000-0000-000000000002', CURRENT_DATE - 6, 'completed', 15),
                                                                                 ('c0000001-0000-0000-0000-000000000008', 'b0000001-0000-0000-0000-000000000002', CURRENT_DATE - 5, 'skipped',    0),
                                                                                 ('c0000001-0000-0000-0000-000000000009', 'b0000001-0000-0000-0000-000000000002', CURRENT_DATE - 4, 'completed', 15),
                                                                                 ('c0000001-0000-0000-0000-000000000010', 'b0000001-0000-0000-0000-000000000002', CURRENT_DATE - 3, 'completed', 15),
                                                                                 ('c0000001-0000-0000-0000-000000000011', 'b0000001-0000-0000-0000-000000000004', CURRENT_DATE - 2, 'completed', 10),
                                                                                 ('c0000001-0000-0000-0000-000000000012', 'b0000001-0000-0000-0000-000000000004', CURRENT_DATE - 1, 'completed', 10),
                                                                                 ('c0000001-0000-0000-0000-000000000013', 'b0000001-0000-0000-0000-000000000006', CURRENT_DATE - 3, 'completed', 25),
                                                                                 ('c0000001-0000-0000-0000-000000000014', 'b0000001-0000-0000-0000-000000000006', CURRENT_DATE - 2, 'completed', 25),
                                                                                 ('c0000001-0000-0000-0000-000000000015', 'b0000001-0000-0000-0000-000000000006', CURRENT_DATE - 1, 'missed',     0),
                                                                                 ('c0000001-0000-0000-0000-000000000016', 'b0000001-0000-0000-0000-000000000007', CURRENT_DATE - 1, 'completed', 10);


INSERT INTO achievements (achievement_id, title, description, badge, xp_required) VALUES
                                                                                      ('d0000001-0000-0000-0000-000000000001', 'First Step',   'Complete your first habit log',       'badge_first_step.png',   0),
                                                                                      ('d0000001-0000-0000-0000-000000000002', 'On a Roll',    'Complete a habit 3 days in a row',    'badge_on_a_roll.png',    100),
                                                                                      ('d0000001-0000-0000-0000-000000000003', 'Week Warrior', 'Complete a habit 7 days in a row',    'badge_week_warrior.png', 300),
                                                                                      ('d0000001-0000-0000-0000-000000000004', 'Level Up',     'Reach level 2',                       'badge_level_up.png',     500),
                                                                                      ('d0000001-0000-0000-0000-000000000005', 'Habit Master', 'Have 5 active habits simultaneously', 'badge_habit_master.png', 1000),
                                                                                      ('d0000001-0000-0000-0000-000000000006', 'XP Grinder',   'Accumulate 2000 XP total',            'badge_xp_grinder.png',   2000);


INSERT INTO app_user_achievements (app_user_achievement_id, app_user_id, achievement_id, earned_at) VALUES
                                                                                                        ('e0000001-0000-0000-0000-000000000001', '550e8400-0001-0000-0000-000000000001', 'd0000001-0000-0000-0000-000000000001', NOW() - INTERVAL '6 days'),
                                                                                                        ('e0000001-0000-0000-0000-000000000002', '550e8400-0001-0000-0000-000000000001', 'd0000001-0000-0000-0000-000000000002', NOW() - INTERVAL '4 days'),
                                                                                                        ('e0000001-0000-0000-0000-000000000003', '550e8400-0001-0000-0000-000000000001', 'd0000001-0000-0000-0000-000000000003', NOW() - INTERVAL '1 day'),
                                                                                                        ('e0000001-0000-0000-0000-000000000004', '550e8400-0001-0000-0000-000000000001', 'd0000001-0000-0000-0000-000000000004', NOW() - INTERVAL '5 days'),
                                                                                                        ('e0000001-0000-0000-0000-000000000005', '550e8400-0001-0000-0000-000000000002', 'd0000001-0000-0000-0000-000000000001', NOW() - INTERVAL '2 days'),
                                                                                                        ('e0000001-0000-0000-0000-000000000006', '550e8400-0001-0000-0000-000000000003', 'd0000001-0000-0000-0000-000000000001', NOW() - INTERVAL '10 days'),
                                                                                                        ('e0000001-0000-0000-0000-000000000007', '550e8400-0001-0000-0000-000000000003', 'd0000001-0000-0000-0000-000000000002', NOW() - INTERVAL '8 days'),
                                                                                                        ('e0000001-0000-0000-0000-000000000008', '550e8400-0001-0000-0000-000000000003', 'd0000001-0000-0000-0000-000000000003', NOW() - INTERVAL '3 days'),
                                                                                                        ('e0000001-0000-0000-0000-000000000009', '550e8400-0001-0000-0000-000000000003', 'd0000001-0000-0000-0000-000000000004', NOW() - INTERVAL '9 days'),
                                                                                                        ('e0000001-0000-0000-0000-000000000010', '550e8400-0001-0000-0000-000000000003', 'd0000001-0000-0000-0000-000000000006', NOW() - INTERVAL '1 day');