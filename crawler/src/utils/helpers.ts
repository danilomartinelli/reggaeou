export function range(size: number, startAt = 0) {
  return Array.from({ length: size }, (_, i) => i + startAt);
}

export function getMonth(value: number) {
  switch (value) {
    case 0:
      return "JAN";
    case 1:
      return "FEV";
    case 2:
      return "MAR";
    case 3:
      return "ABR";
    case 4:
      return "MAI";
    case 5:
      return "JUN";
    case 6:
      return "JUL";
    case 7:
      return "AGO";
    case 8:
      return "SET";
    case 9:
      return "OUT";
    case 10:
      return "NOV";
    case 11:
      return "DEZ";
  }
}
