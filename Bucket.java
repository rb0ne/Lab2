public class Bucket {
	String key;
	int value;
	Bucket nextBucket;

	public Bucket(String name, int pos, Bucket bucket) {
		if (name == null)
			throw new NullPointerException();
		key = name;
		value = pos;
		nextBucket = bucket;
	}

	public String getKey() {
		return key;
	}

	public int getValue() {
		return value;
	}

	public Bucket findBucket(String name) {
		if (key.equalsIgnoreCase(name)) {
			return this;
		}
		if (nextBucket == null) {
			return null;
		} else {
			return nextBucket.findBucket(name);
		}
	}

	public void setValue(int newValue) {
		value = newValue;
	}
	
	public Bucket getNextBucket() {
		return nextBucket;
	}

	public void setNextBucket(Bucket newNextBucket) {
		nextBucket = newNextBucket;
	}
}
