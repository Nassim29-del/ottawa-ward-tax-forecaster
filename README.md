# Ottawa Ward Tax Forecaster

A Java application that uses **linear regression** to analyze and forecast
municipal tax data for the wards of Ottawa, based on the publicly available
[Open Ottawa "Taxes by Ward" dataset](https://open.ottawa.ca/).

Built as an exercise in object-oriented design — abstract classes,
inheritance, polymorphism, encapsulation, and Java records.

## What it does

The program offers two kinds of analysis:

1. **Forecast existing ward data** — pick a ward and a future year, and the
   program predicts its value assessment, municipal tax, and share of total
   taxes by fitting a regression line to that ward's historical figures.
2. **Predict new ward municipal taxes** — choose a ward type (Rural,
   Suburban, Urban) and an estimated value assessment, and the program
   predicts a likely municipal tax range.

## How it works

Each prediction is powered by simple linear regression, which fits the
best straight line (`y = mx + c`) through historical data points and uses it
to estimate values for new inputs. For new-ward predictions, the standard
error of the regression is used to produce a range rather than a single
figure.

## Design overview

| Class | Role |
|-------|------|
| `WardAnalysis` | Application entry point (`main`) |
| `WardManager` | Drives the main menu and coordinates analyses |
| `Analysis` | Abstract base class for all analysis types |
| `ExistingWardAnalysis` | Forecasts values for an existing ward |
| `NewWardAnalysis` | Predicts tax range for a proposed new ward |
| `Ward` | Holds ward data and its list of yearly tax records |
| `Tax` | Holds a single year's tax figures for a ward |
| `Point` | Immutable (x, y) data point (Java `record`) |
| `Regression` | Fits a linear regression and makes predictions |

`Analysis` defines a common `runAnalysis` contract that both concrete
analysis types implement, so `WardManager` can run whichever one is
selected without knowing its concrete type (polymorphism).

## Running it

```bash
# Compile
javac *.java

# Run
java WardAnalysis
```

At the menu, read the data files first (`R`), choose an analysis type (`C`),
then run it (`A`).

### Data files

The program expects two CSV files: one describing the wards and one
containing their yearly tax records. Update the file paths in the code (or
place the CSVs alongside the program) before reading.

## Attribution

- The `Regression` class is adapted from the linear regression
  implementation by **Robert Sedgewick and Kevin Wayne**
  (*Algorithms*, Princeton University), modified to operate on a list of
  `Point` records.
- The `Point` record and the base `Regression` algorithm were provided as
  part of the coursework; the analysis layer (`Analysis`,
  `ExistingWardAnalysis`, `NewWardAnalysis`, `Ward`, `Tax`, `WardManager`,
  `WardAnalysis`) is my own work.
- Tax data is derived from the City of Ottawa's Open Data portal.

## Author

**Nasim Bidel**
