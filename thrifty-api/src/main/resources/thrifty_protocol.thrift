#!/usr/local/bin/thrift --gen java --gen py --gen php --gen perl --gen rb --gen csharp --gen cpp

namespace java com.videoplaza.thrifty
namespace cpp com.videoplaza.thrifty
namespace csharp Videoplaza.Thrifty
namespace py thrifty
namespace php thrifty
namespace perl Thrifty
namespace rb VideoplazaThrifty

const string VERSION = "0.1.0"

struct Column {
	1: required binary name,
	2: optional binary value,
	3: optional i64 timestamp,
	4: optional i32 ttl,
}

struct Location {
	1: required double latitude,
	2: required double longitude,
}

exception InvalidRequestException {
    1: required string why
}

/**
 * Response from ThriftyService.whatIsHappening
 * @param someString, some string
 * @param listOfStrings, a list of strings
 */
struct BigResponse {
	1: required string someString,
	2: optional list<string> listOfStrings,
	3: optional set<string> setOfStrings,
	4: optional map<string, string> mapOfStringStrings,
	5: optional i32 age,
	6: optional string language = "english",
	7: optional Location location,
	8: optional map<string, Location> locationsByName,
}

struct BigRequest {
	1: required string name
}

/**
 * Common status reporting mechanism across all services
 */
enum Status {
	DEAD = 0,
	STARTING = 1,
	ALIVE = 2,
	STOPPING = 3,
	STOPPED = 4,
	WARNING = 5,
}

/**
 * Thrifty service
 */
service ThriftyService {

	/**
	 * Returns a descriptive name of the service
	 */
	string getName(),

	/**
	 * Returns the version of the service
	 */
	string getVersion(),

	/**
	 * Gets the status of this service
	 */
	Status getStatus(),

	/**
	 * Complex method call
	 */
	BigResponse whatIsHappening(1: BigRequest request) throws (1: InvalidRequestException ire)

}