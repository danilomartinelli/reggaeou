import { ibahiaCrawler } from "./ibahia";
import { symplaCrawler } from "./sympla";

export enum Vendor {
  IBAHIA = "IBahia",
  SYMPLA = "Sympla"
}

export interface IData {
  href: string;
  title: string;
  time: string;
  local: string;
  month: string;
  day: string;
  folder: string;
  vendor: Vendor;
}

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
