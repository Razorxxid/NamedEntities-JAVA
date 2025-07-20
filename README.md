# NamedEntities-JAVA

This project implements a simple command line tool that fetches articles from configured news feeds (RSS or Reddit) and applies heuristics to count named entities within the retrieved texts.

## Project structure

- `src/` – Java source code
  - `FeedReaderMain.java` – entry point of the application
  - `feed/` – models for feeds and articles
  - `httpRequest/` – helpers for building URLs, performing HTTP requests and saving the results
  - `parser/` – parsers for RSS, Reddit and the subscription JSON file
  - `namedEntity/` – named entity classes and heuristics (`QuickHeuristic`, `RandomHeuristic`)
  - `subscription/` – subscription objects used by the reader
- `config/subscriptions.json` – list of feed subscriptions

## Getting started

1. Install a JDK (8 or later). The program also requires the `org.json` library available on the classpath.
2. Compile the sources:

   ```bash
   javac -cp json.jar src/*/*.java src/*/*/*.java
   ```

3. Run the reader:

   ```bash
   java -cp json.jar:src FeedReaderMain
   ```

The program loads the subscriptions from `config/subscriptions.json`, retrieves the selected feed, parses the articles and prints the detected named entities using the `QuickHeuristic`.

## Configuration

Each subscription entry defines a URL template with parameters and a feed type (`rss` or `reddit`). Example from `config/subscriptions.json`:

```json
{
    "url": "https://rss.nytimes.com/services/xml/rss/nyt/%s.xml",
    "urlParams": ["Business", "Technology"],
    "urlType": "rss"
}
```

## Named Entity Heuristics

`QuickHeuristic` filters stopwords and merges consecutive capitalised words to detect candidate entities. `RandomHeuristic` is a toy heuristic that randomly classifies words.

This README provides a short overview of the repository structure and basic usage.
