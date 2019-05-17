import { Pool } from "pg";

export function connect() {
  const pool = new Pool({
    user: "daniloleonemartinelli",
    host: "localhost",
    database: "reggaeou",
    password: "",
    port: 5432
  });

  return pool;
}
