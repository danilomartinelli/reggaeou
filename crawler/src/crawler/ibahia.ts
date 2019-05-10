import { launch } from "puppeteer";
import { IData, Vendor } from "../utils/types";
import { range, getMonth } from "../utils/helpers";

async function app() {
  const browser = await launch();
  const page = await browser.newPage();

  await page.goto("http://www2.ibahia.com/shows", {
    waitUntil: "networkidle2"
  });

  const childElementCount: number = await page.evaluate(sel => {
    return document.querySelector(sel).childElementCount;
  }, "#tab1 > ul");
  const arrRange = range(childElementCount, 1);

  const data: IData[] = [];

  await Promise.all(
    arrRange.map(async nth => {
      const href: string = await page.evaluate(sel => {
        return document
          .querySelector(sel)
          .getAttribute("href")
          .trim();
      }, `#tab1 > ul > li:nth-child(${nth}) > a`);

      const title: string = await page.evaluate(sel => {
        return document.querySelector(sel).innerText.trim();
      }, `#tab1 > ul > li:nth-child(${nth}) > h1 > span.sub-titulo-preto-12a`);

      const time: string = await page.evaluate(sel => {
        return document.querySelector(sel).innerText.trim();
      }, `#tab1 > ul > li:nth-child(${nth}) > h2`);

      const local: string = await page.evaluate(sel => {
        return document.querySelector(sel).innerText.trim();
      }, `#tab1 > ul > li:nth-child(${nth}) > h1 > span.texto-preto-10`);

      const date = new Date();

      const folder: string = await page.evaluate(sel => {
        return `http://www2.ibahia.com/${document
          .querySelector(sel)
          .getAttribute("src")}`;
      }, `#tab1 > ul > li:nth-child(${nth}) > a > img`);

      const vendor = Vendor.IBAHIA;

      const description = "";

      const categoryId = 1;

      data.push({
        href,
        title,
        description,
        time,
        local,
        date,
        folder,
        vendor,
        categoryId
      });
    })
  );

  await browser.close();

  return data;
}

export const ibahiaCrawler = app;
