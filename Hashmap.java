public class Hashmap {
	int size;
	Bucket[] hashTable;

	public Hashmap() {
		size = 10;
		hashTable = new Bucket[size];
	}

	private int hashFkt(String name) {
		int nameToHash = Math.abs(name.hashCode());
		int pos = nameToHash % size;
		return pos;
	}

	public void add(String name, int heapPos) {
		int hashPos = hashFkt(name);
		Bucket old = hashTable[hashPos];
		Bucket hashObject = new Bucket(name, heapPos, old);
		hashTable[hashPos] = hashObject;
	}

	public int read(String name) {
		int hashPos = hashFkt(name);
		Bucket bucket = hashTable[hashPos].findBucket(name);
		int heapPos = bucket.getValue();
		return heapPos;
	}

	public void remove(String name) {
		int hashPos = hashFkt(name);
		if (hashTable[hashPos].getKey().equalsIgnoreCase(name)) {
			hashTable[hashPos] = hashTable[hashPos].getNextBucket();
		}
		else {
			remove(name, hashTable[hashPos], hashTable[hashPos].getNextBucket());
		}
	}
	
	private void remove(String name, Bucket bucket, Bucket bucketToRemove) {
		if (bucketToRemove.getKey().equalsIgnoreCase(name)) {
			bucket.setNextBucket(bucketToRemove.getNextBucket());
		}
		else {
		remove(name, bucketToRemove, bucketToRemove.getNextBucket());
		}
	}
	
	public void changeHeapPos(String name, int newPos){
		if (!hasName(name))
			return;
		int hashPos = hashFkt(name);
		Bucket bucket = hashTable[hashPos].findBucket(name);
		bucket.setValue(newPos);
	}
	
	public boolean hasName(String name){
		int hashPos = hashFkt(name);
		Bucket bucket = null;
		if (hashTable[hashPos] != null){
			bucket = hashTable[hashPos].findBucket(name);
			return bucket != null;
		}
		return bucket != null;
	}
}
