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
