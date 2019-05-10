import { launch } from "puppeteer";
import { IData, Vendor } from "../utils/types";
import { range } from "../utils/helpers";

async function app() {
  const browser = await launch();
  const page = await browser.newPage();

  await page.goto(
    "https://www.sympla.com.br/eventos/salvador-ba/show-musica-festa",
    { waitUntil: "networkidle2" }
  );

  const childElementCount: number = await page.evaluate(sel => {
    return document.querySelector(sel).childElementCount;
  }, "#events-grid");
  const arrRange = range(childElementCount, 1);

  const data: IData[] = [];

  await Promise.all(
    arrRange.map(async nth => {
      const href: string = await page.evaluate(sel => {
        return document
          .querySelector(sel)
          .getAttribute("href")
          .trim();
      }, `#events-grid > div:nth-child(${nth}) > a`);

      const title: string = await page.evaluate(sel => {
        return document.querySelector(sel).innerText.trim();
      }, `#events-grid > div:nth-child(${nth}) > a > div.event-name > p`);

      const time: string = await page.evaluate(sel => {
        return document
          .querySelector(sel)
          .innerText.trim()
          .split(" ")[0];
      }, `#events-grid > div:nth-child(${nth}) > a > div.event-local-box > div:nth-child(2)`);

      const local: string = await page.evaluate(sel => {
        return document
          .querySelector(sel)
          .innerText.trim()
          .split("  ")[1];
      }, `#events-grid > div:nth-child(${nth}) > a > div.event-local-box > div:nth-child(2)`);

      const month: string = await page.evaluate(sel => {
        return document.querySelector(sel).innerText.trim();
      }, `#events-grid > div:nth-child(${nth}) > a > div.calendar-box > div.calendar-month`);

      const day: string = await page.evaluate(sel => {
        return document.querySelector(sel).innerText.trim();
      }, `#events-grid > div:nth-child(${nth}) > a > div.calendar-box > div.calendar-day`);

      const folder: string = await page.evaluate(sel => {
        return document
          .querySelector(sel)
          .getAttribute("style")
          .replace("background-image:url('", "")
          .replace("')", "");
      }, `#events-grid > div:nth-child(${nth}) > a > div.event-image-box`);

      const vendor = Vendor.SYMPLA;
      data.push({ href, title, time, local, month, day, folder, vendor });
    })
  );

  await browser.close();

  return data;
}

export const symplaCrawler = app;
