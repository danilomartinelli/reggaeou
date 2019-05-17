import { connect } from "./db/config";

const db = connect();

async function getTechEvents() {
  const res = await db.query(
    "SELECT href, title, description, local FROM events WHERE vendor = 'Sympla' AND created_at > NOW() - INTERVAL '1 DAY' AND created_at <= NOW() + INTERVAL '1 DAY'"
  );
  const rows = res.rows;

  return rows.map(row => ({
    href: row.href,
    title: row.title,
    description: row.description,
    local: row.local
  }));
}

async function getTechUsers() {
  const res = await db.query(
    "select email from users where id_user in (select id_user from user_category where id_category = 2) AND status = 'Active'"
  );
  const rows = res.rows;

  return rows.map(row => ({
    email: row.email
  }));
}

async function app() {
  // Tech
  const techEvents = await getTechEvents();
  const techUsers = await getTechUsers();

  console.log(techEvents, techUsers);
}

app();
