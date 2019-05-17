import { launch } from "puppeteer";
import { IData, Vendor } from "../utils/types";
import { range } from "../utils/helpers";

async function app() {
  const browser = await launch();
  const page = await browser.newPage();

  await page.goto("https://www.sympla.com.br/eventos/tecnologia", {
    waitUntil: "networkidle2"
  });

  const childElementCount: number = await page.evaluate(sel => {
    return document.querySelector(sel).childElementCount;
  }, "#events-grid > div > div > ul");
  const arrRange = range(childElementCount, 1);

  const data: IData[] = [];

  await Promise.all(
    arrRange.map(async nth => {
      const href: string = await page.evaluate(sel => {
        return document
          .querySelector(sel)
          .getAttribute("href")
          .trim();
      }, `#events-grid > div > div > ul > li:nth-child(${nth}) > a`);

      const title: string = await page.evaluate(sel => {
        return document.querySelector(sel).innerText.trim();
      }, `#events-grid > div > div > ul > li:nth-child(${nth}) > a > div.event-info-block.event-card-list-var.w-clearfix > div.event-name.event-card > span`);

      const time: string = await page.evaluate(sel => {
        return document
          .querySelector(sel)
          .innerText.trim()
          .split(" ")[0];
      }, `#events-grid > div > div > ul > li:nth-child(${nth}) > a > div.event-info-block.event-card-list-var.w-clearfix > div.event-card-data-block > div > div > div.event-card-info`);

      const local: string = await page.evaluate(sel => {
        return document.querySelector(sel).innerText.trim();
      }, `#events-grid > div > div > ul > li:nth-child(${nth}) > a > div.event-info-block.event-card-list-var.w-clearfix > div.event-location.event-card`);

      const month: string = await page.evaluate(sel => {
        return document.querySelector(sel).innerText.trim();
      }, `#events-grid > div > div > ul > li:nth-child(${nth}) > a > div.event-info-block.event-card-list-var.w-clearfix > div.event-card-data-block > div > div > div.event-card-day.w-clearfix > div.event-card-date-month`);

      const day: string = await page.evaluate(sel => {
        return document.querySelector(sel).innerText.trim();
      }, `#events-grid > div > div > ul > li:nth-child(${nth}) > a > div.event-info-block.event-card-list-var.w-clearfix > div.event-card-data-block > div > div > div.event-card-day.w-clearfix > div.event-date-day`);

      const date = new Date();

      const folder: string = await page.evaluate(sel => {
        return (
          "https://www.sympla.com.br" +
          document.querySelector(sel).getAttribute("src")
        );
      }, `#events-grid > div > div > ul > li:nth-child(${nth}) > a > div:nth-child(1) > div > img`);

      const vendor = Vendor.SYMPLA;

      const description = "";

      const categoryId = 2;

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

export const symplaCrawler = app;
