import { ibahiaCrawler, symplaCrawler } from "./crawler";
import { connect } from "./db/config";

const db = connect();

async function submitData() {
  const text = `
  INSERT INTO 
  events(title, description, href, local, date, time, folder, id_category, vendor) 
  VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9)
  ON CONFLICT (href)
  DO NOTHING
  `;

  const dataIbahia = await ibahiaCrawler();

  await Promise.all(
    dataIbahia.map(async e => {
      const values = [
        e.title,
        e.description,
        e.href,
        e.local,
        e.date,
        e.time,
        e.folder,
        e.categoryId,
        e.vendor
      ];

      await db.query(text, values);
    })
  );

  const dataSympla = await symplaCrawler();

  await Promise.all(
    dataSympla.map(async e => {
      const values = [
        e.title,
        e.description,
        e.href,
        e.local,
        e.date,
        e.time,
        e.folder,
        e.categoryId,
        e.vendor
      ];

      await db.query(text, values);
    })
  );
}

async function app() {
  await submitData();
}

app();
