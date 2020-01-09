import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FileList<E> extends FileContainer implements List<E> {

//	public List<E> list;
	private RandomAccessFile raf;
//	transient Object[] elementData;
	private int size = 0;
//	PrintWriter pWriter = new PrintWriter(filename);
//	String filename = String.valueOf(System.currentTimeMillis()) + ".list";

	public FileList(String name) {
//		list = new ArrayList<E>();
		fileName = name;
		try {
			raf = new RandomAccessFile(fileName, "rw");
//			getListFromFile();
//			raf.setLength(0);
			if (raf.length() != 0) {
				while (raf.getFilePointer() < raf.length()) {
					raf.readLine();
					size++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FileList() {
//		list = new ArrayList<E>();
		fileName = "defaultName.list";
		try {
			raf = new RandomAccessFile(fileName, "rw");
//			raf.setLength(0);
			if (raf.length() != 0) {
				while (raf.getFilePointer() < raf.length()) {
					raf.readLine();
					size++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		List test = new FileList<Number>("123.list");
		test.add(1);
		test.add(2);
		test.add(3);

		test = new FileList<Number>("456.list");
		test.add(4);
		test.add(-5.0);
		test.add(6e0);

	}

//	public void getListFromFile() throws IOException {
//		String type = raf.readLine();
//		String number = raf.readLine();
//		
//		while(type != null) {
//			if(type .equals("Integer")) {
//				Integer num = new Integer(number);
//				((List<Number>)list).add(num);
//			}
//			else if(type .equals("Double")){
//				Double num = new Double(number);
//				((List<Number>)list).add(num);
//			}
//			else if(type .equals("Float")){
//				Float num = new Float(number);
//				((List<Number>)list).add(num);
//			}
//			else if(type .equals("Long")){
//				Long num = new Long(number);
//				((List<Number>)list).add(num);
//			}
//			else if(type .equals("Short")){
//				Short num = new Short(number);
//				((List<Number>)list).add(num);
//			}
//		}
//			
//	}

	@Override
	public String toString() {
//		String result = "";
//		for(int i = 0; i<list.size(); i++) {
//			result += list.get(i) + ",";
//		}
//		return result;

		StringBuilder str = new StringBuilder();
		try {
			raf.seek(0);
			str.append("[");

			if (raf.getFilePointer() < raf.length()) {
				str.append(raf.readLine());
			}

			while (raf.getFilePointer() < raf.length()) {
				str.append(", ");
				str.append(raf.readLine());
			}
			str.append("]");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return str.toString();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(int index, E element) {

		if (index > size) {
			throw new IndexOutOfBoundsException(String.valueOf(index));
		} else {
			try {
				raf.seek(0);
				int line = 0;
				long location = 0;
				StringBuilder str = new StringBuilder();

				while (raf.getFilePointer() < raf.length()) {

					if (line == index) {
						location = raf.getFilePointer();

						while (raf.getFilePointer() < raf.length()) {
							str.append(raf.readLine());
							str.append('\n');
						}
						break;
					} else {
						raf.readLine();
						line += 1;
					}
				}
				raf.seek(location);
				raf.writeBytes(String.valueOf(element)); // add
				raf.write('\n');

				raf.writeBytes(str.toString());
				size++;

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public E remove(int index) {

		if (index > size) {
			throw new IndexOutOfBoundsException(String.valueOf(index));
		} else {
			try {
				raf.seek(0);
				int line = 0;
				long location = 0;
				StringBuilder temp = new StringBuilder();

				while (raf.getFilePointer() < raf.length()) {
					if (line == index) {
						location = raf.getFilePointer();
						raf.readLine();

						while (raf.getFilePointer() < raf.length()) {
							temp.append(raf.readLine());
							temp.append('\n');
						}
						break;
					}
					raf.readLine();
					line += 1;
				}
				raf.seek(location);

				raf.writeBytes(temp.toString());
				raf.setLength(location + temp.toString().length());
				size -= 1;

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}
	}

	@Override
	public void clear() {
//	    list.addAll(null);
//		list.clear();
		try {
			raf.close();
			FileWriter fileWriter = new FileWriter(fileName);// open
			fileWriter.write("");
			fileWriter.flush();// set null
			fileWriter.close();

			raf = new RandomAccessFile(fileName, "rws");
			size = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
//		return list.add(e);
//		String str = e.toString();
//		String a = null;
//		for(int i=0;i < str.length();i++) {
//			int num = Character.getNumericValue(str.charAt(i));
//			if(num>= 1 && num <= 9 ) {
//				a = a+Integer.toString(num);
//			}
//
//		}
//		if(a.isEmpty()) {
//			return false;
//		}
//		else {
//			list.add((E) a);
//			return true;
//		}
//		if(str.matches(".*\\d+.*")) {
//			return true;
//		}
//		else {
//			return false;
//		}
		try {
			// find length
			raf.seek(raf.length());
			raf.writeBytes(String.valueOf(e));// write
			raf.write('\n');
		} catch (IOException e0) {
			e0.printStackTrace();
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
//		return list.indexOf(o) >= 0;
//		if (list.contains(o)) {
//			list.remove(o);
//			return true;
//		} else {
//			return false;
//		}

		boolean success = false;

		try {
			raf.seek(0);
			StringBuilder temp = new StringBuilder();
			String line;
			long loc = 0;

			// traverse the file
			while (raf.getFilePointer() < raf.length()) {
				loc = raf.getFilePointer();
				line = raf.readLine();

				if (line.equals(String.valueOf(o))) {

					while (raf.getFilePointer() < raf.length()) {
						temp.append(raf.readLine());
						temp.append('\n');
					}
					success = true;
					break;
				}
			}
			if (success) {
				raf.seek(loc);

				raf.writeBytes(temp.toString());
				raf.setLength(loc + temp.toString().length());
				size -= 1;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return success;
	}

//	private void grow(){
//		int oldCapacity = list.size();
//		int newCapacity = oldCapacity << 1;
//		elementData = Arrays.copyOf(elementData, newCapacity);
//		//System.out.println(oldCapacity +":" + newCapacity);
//	}
//
//	private void shrink(){
//		int oldCapacity = elementData.length;
//		int newCapacity = oldCapacity >> 1;
//		elementData = Arrays.copyOf(elementData, newCapacity);
//		//System.out.println(oldCapacity +":" + newCapacity);
//	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E get(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getFileName() {
//		throw new UnsupportedOperationException();
		return fileName;
	}

	@Override
	public long getFileSize() {
		return size;
	}

}
