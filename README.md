# 📬 Subscription Service

> _I initially considered integrating Kafka, but decided it's not necessary for the current scope._

## 🛠 Environment Configuration

Before running the application, create a `.env` file in the project root with the following variables:

```env
# 🔐 Subscriptions Database
POSTGRES_USER_SUBSCRIPTIONS=username
POSTGRES_PASSWORD_SUBSCRIPTIONS=pass
POSTGRES_DB_SUBSCRIPTIONS=db_name

# 👤 Users Database
POSTGRES_USER_USERS=username
POSTGRES_PASSWORD_USERS=pass
POSTGRES_DB_USERS=db_name
