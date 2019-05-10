import { ibahiaCrawler, symplaCrawler } from "./crawler";

async function getData() {
  const dataIbahia = await ibahiaCrawler();
  const dataSympla = await symplaCrawler();

  return [...dataIbahia, ...dataSympla];
}

async function app() {
  const data = await getData();
  console.log(data);
}

app();
