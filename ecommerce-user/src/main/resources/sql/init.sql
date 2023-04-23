CREATE TABLE "users".registration_outbox
(
    id            uuid          NOT NULL,
    transaction_at    TIMESTAMP WITH TIME ZONE,
    publish_at  TIMESTAMP WITH TIME ZONE,
    payload       jsonb         NOT NULL,
    outbox_status varchar NOT NULL,
    version       integer       NOT NULL,
)

CREATE INDEX "users_outbox_saga_status"
 ON "users".registration_outbox(outbox_status)
