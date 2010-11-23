/**
 * Buckets with the properties: name, position.
 * 
 * @author Robin Persson Sšderholm, Andreas KŠllberg
 * 
 */
public class Bucket {
	String key;
	int value;
	Bucket nextBucket;

	/**
	 * Constructs a new Bucket with "name", "pos" and a pointer to a Bucket
	 * (might be a null pointer).
	 * 
	 * @param name
	 * @param pos
	 * @param bucket
	 */
	public Bucket(String name, int pos, Bucket bucket) {
		if (name == null)
			throw new NullPointerException();
		key = name;
		value = pos;
		nextBucket = bucket;
	}

	/**
	 * Returns the key.
	 * 
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Returns the value.
	 * 
	 * @return
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Returns the Bucket with name = "name", if there is no such Bucket it
	 * returns null.
	 * 
	 * @param name
	 * @return
	 */
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

	/**
	 * Sets value=newValue.
	 * @param newValue
	 */
	public void setValue(int newValue) {
		value = newValue;
	}
	
	/**
	 * Returns the nextBucket.
	 * @return
	 */
	public Bucket getNextBucket() {
		return nextBucket;
	}
	
	/**
	 * Sets the nextBucket to newNextBucket.
	 * @param newNextBucket
	 */
	public void setNextBucket(Bucket newNextBucket) {
		nextBucket = newNextBucket;
	}
}
