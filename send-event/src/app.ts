import { connect } from "./db/config";

const db = connect();

async function getTech() {
  const res = await db.query(
    "SELECT * FROM events WHERE vendor = 'Sympla' AND created_at > NOW() - INTERVAL '1 DAY' AND created_at <= NOW() + INTERVAL '1 DAY'"
  );
  const rows = res.rows;

  return rows.map(row => ({
    href: row.href,
    title: row.title,
    description: row.description,
    local: row.local
  }));
}

async function app() {
  // Tech
  const techEvents = await getTech();

  console.log(techEvents);
}

app();
