/**
 * Hashmap implemented as an array with buckets.
 * 
 * @author Robin Persson Sšderholm, Andreas KŠllberg
 * 
 */
public class Hashmap {
	int size;
	Bucket[] hashTable;

	/**
	 * Constructs a new Hashmap
	 */
	public Hashmap() {
		size = 10;
		hashTable = new Bucket[size];
	}

	/**
	 * Returns the hashed value of "name".
	 * 
	 * @param name
	 * @return
	 */
	private int hashFkt(String name) {
		int nameToHash = Math.abs(name.hashCode());
		int pos = nameToHash % size;
		return pos;
	}

	/**
	 * Adds "heapPos" in a Bucket at position hashFkt(name).
	 * 
	 * @param name
	 * @param heapPos
	 */
	public void add(String name, int heapPos) {
		int hashPos = hashFkt(name);
		Bucket old = hashTable[hashPos];
		Bucket hashObject = new Bucket(name, heapPos, old);
		hashTable[hashPos] = hashObject;
	}

	/**
	 * Returns the value of the Bucket that's stores information about "name".
	 * 
	 * @param name
	 * @return
	 */
	public int read(String name) {
		int hashPos = hashFkt(name);
		Bucket bucket = hashTable[hashPos].findBucket(name);
		int heapPos = bucket.getValue();
		return heapPos;
	}

	/**
	 * Removes the Bucket containing information about "name".
	 * 
	 * @param name
	 */
	public void remove(String name) {
		int hashPos = hashFkt(name);
		if (hashTable[hashPos].getKey().equalsIgnoreCase(name)) {
			hashTable[hashPos] = hashTable[hashPos].getNextBucket();
		} else {
			remove(name, hashTable[hashPos], hashTable[hashPos].getNextBucket());
		}
	}

	private void remove(String name, Bucket bucket, Bucket bucketToRemove) {
		if (bucketToRemove.getKey().equalsIgnoreCase(name)) {
			bucket.setNextBucket(bucketToRemove.getNextBucket());
		} else {
			remove(name, bucketToRemove, bucketToRemove.getNextBucket());
		}
	}

	/**
	 * Change the value of the Bucket that contains information about "name".
	 * 
	 * @param name
	 * @param newPos
	 */
	public void changeHeapPos(String name, int newPos) {
		if (!hasName(name))
			return;
		int hashPos = hashFkt(name);
		Bucket bucket = hashTable[hashPos].findBucket(name);
		bucket.setValue(newPos);
	}

	/**
	 * Returns true if there is a Bucket containing information about "name".
	 * 
	 * @param name
	 * @return
	 */
	public boolean hasName(String name) {
		int hashPos = hashFkt(name);
		Bucket bucket = null;
		if (hashTable[hashPos] != null) {
			bucket = hashTable[hashPos].findBucket(name);
			return bucket != null;
		}
		return bucket != null;
	}
}
