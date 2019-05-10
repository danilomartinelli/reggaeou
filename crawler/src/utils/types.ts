export enum Vendor {
  IBAHIA = "IBahia",
  SYMPLA = "Sympla"
}

export interface IData {
  href: string;
  title: string;
  description: string;
  time: string;
  local: string;
  date: Date;
  folder: string;
  vendor: Vendor;
  categoryId: number;
}
