package ddalgi;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class MyWritableComparable implements 
	WritableComparable<MyWritableComparable> {
	
	private int counter;
	private long timestamp;
	
	@Override
	public void write(DataOutput out) throws IOException{
		out.writeInt(counter);
		out.writeLong(timestamp);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.counter = in.readInt();
		this.timestamp = in.readLong();
		
	}

	@Override
	public int compareTo(MyWritableComparable w) {
		//내림차순 정렬
		if(this.counter > w.counter) {
			return -1;
		} else if(this.counter < w.counter) {
			return 1;
		} else {
			if (this.timestamp < w.timestamp) {
				return 1;
			} else if(this.timestamp > w.timestamp) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
