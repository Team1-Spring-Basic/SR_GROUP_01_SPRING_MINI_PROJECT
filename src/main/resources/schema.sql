

CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- DROP TABLE

DROP TABLE IF EXISTS app_user_achievements CASCADE;
DROP TABLE IF EXISTS achievements           CASCADE;
DROP TABLE IF EXISTS habit_logs            CASCADE;
DROP TABLE IF EXISTS habits                CASCADE;
DROP TABLE IF EXISTS app_user_roles        CASCADE;
DROP TABLE IF EXISTS app_roles             CASCADE;
DROP TABLE IF EXISTS app_users             CASCADE;


CREATE TABLE app_users (
                           app_user_id   UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
                           username      VARCHAR(50)  NOT NULL UNIQUE,
                           full_name     VARCHAR(50)  NOT NULL,
                           email         VARCHAR(100) NOT NULL UNIQUE,
                           password      VARCHAR(255) NOT NULL,
                           level         INT          NOT NULL DEFAULT 1,
                           xp            INT          NOT NULL DEFAULT 0,
                           profile_image TEXT,
                           is_verified   BOOLEAN      NOT NULL DEFAULT FALSE,
                           created_at    TIMESTAMPTZ  NOT NULL DEFAULT NOW()
);


CREATE TABLE app_roles (
                           role_id UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
                           name    VARCHAR(50) NOT NULL UNIQUE
);


CREATE TABLE app_user_roles (
                                app_user_id UUID NOT NULL REFERENCES app_users(app_user_id) ON DELETE CASCADE,
                                role_id     UUID NOT NULL REFERENCES app_roles(role_id)     ON DELETE CASCADE,
                                PRIMARY KEY (app_user_id, role_id)
);


CREATE TABLE habits (
                        habit_id    UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
                        app_user_id UUID         NOT NULL REFERENCES app_users(app_user_id) ON DELETE CASCADE,
                        title       VARCHAR(100) NOT NULL,
                        description TEXT,
                        frequency   VARCHAR(20)  NOT NULL,
                        is_active   BOOLEAN      NOT NULL DEFAULT TRUE,
                        created_at  TIMESTAMPTZ  NOT NULL DEFAULT NOW()
);


CREATE TABLE habit_logs (
                            habit_log_id UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
                            habit_id     UUID        NOT NULL REFERENCES habits(habit_id) ON DELETE CASCADE,
                            log_date     DATE        NOT NULL DEFAULT CURRENT_DATE,
                            status       VARCHAR(20) NOT NULL,
                            xp_earned    INT         NOT NULL DEFAULT 0,
                            UNIQUE (habit_id, log_date)
);


CREATE TABLE achievements (
                              achievement_id UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
                              title          VARCHAR(100) NOT NULL UNIQUE,
                              description    TEXT,
                              badge          TEXT,
                              xp_required    INT          NOT NULL DEFAULT 0
);


CREATE TABLE app_user_achievements (
                                       app_user_achievement_id UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
                                       app_user_id             UUID        NOT NULL REFERENCES app_users(app_user_id)      ON DELETE CASCADE,
                                       achievement_id          UUID        NOT NULL REFERENCES achievements(achievement_id) ON DELETE CASCADE,
                                       earned_at               TIMESTAMPTZ NOT NULL DEFAULT NOW(),
                                       UNIQUE (app_user_id, achievement_id)
);


CREATE INDEX idx_habits_user         ON habits(app_user_id);
CREATE INDEX idx_habit_logs_habit    ON habit_logs(habit_id);
CREATE INDEX idx_habit_logs_date     ON habit_logs(log_date);
CREATE INDEX idx_aua_user            ON app_user_achievements(app_user_id);
CREATE INDEX idx_aua_achievement     ON app_user_achievements(achievement_id);
CREATE INDEX idx_user_roles_user     ON app_user_roles(app_user_id);
CREATE INDEX idx_user_roles_role     ON app_user_roles(role_id);

