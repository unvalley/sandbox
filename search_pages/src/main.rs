extern crate csv;
extern crate serde;

#[macro_use]
extern crate serde_derive;

use std::error::Error;
use std::io;
use std::process;

#[derive(Debug, Deserialize)]
struct Record {
    id: i64,
    title: String,
    url: String,
    #[serde(deserialize_with = "csv::invalid_option")]
    snippet: Option<String>,
    score: f64,
    task_id: i64,
    good_entity: String,
    bad_entity: String,
    item_id: i64,
    hospital_expertise: String,
}

fn run() -> Result<(), Box<dyn Error>> {
    let mut rdr = csv::Reader::from_reader(io::stdin());
    for result in rdr.deserialize() {
        let record: Record = result?;
        println!("{:?}", record);
    }
    Ok(())
}

fn main() {
    if let Err(err) = run() {
        println!("{}", err);
        process::exit(1);
    }
}
