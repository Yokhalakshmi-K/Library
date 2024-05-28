import java.util.*;
public class Library{
	Map<String,Book> books = new HashMap<>();
	Map<String,Member> members = new HashMap<>();
	List<Transaction> transactions = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
	public void addBook(Book book){
		books.put(book.getId(),book);
	}
	public void addMember(Member member){
		members.put(member.getId(),member);
	}
	public void borrow(String bookId , String memberId){
		Book book = books.get(bookId);
		Member member = members.get(memberId);
		if(book!=null && book.isAvailable() && member!=null){
			book.setAvailable(false);
			book.incrementBorrowCount();
			member.incrementBorrowCount();
			transactions.add(new Transaction(bookId,memberId,new Date(),true));
		}else{
			System.out.println("Book is not available");
		}
	}
	public void returnBook(String bookId , String memberId){
		Book book = books.get(bookId);
		Member member = members.get(memberId);
		if(book!=null && !book.isAvailable() && member!=null){
			book.setAvailable(true);
			transactions.add(new Transaction(bookId,memberId,new Date(),false));
		}else{
			System.out.println("Book is not return");
		}
	}
	public void listAllBooks(){
		System.out.println("Book ID"+"\t\t"+"\tTitle\t\t"+"\t\tAuthor");
		for(Book book : books.values()){
			if(book.isAvailable()){
				System.out.println(book.getId()+"\t\t"+book.getTitle()+"\t\t"+book.getAuthor());
			}
		}
	}
	public void listBorrowBooks(){
		System.out.println("Borrowed books");
		for(Transaction transaction : transactions){
			if(transaction.isBorrow()){
				Book book = books.get(transaction.getBookId());
				Member member = members.get(transaction.getMemId());
				System.out.println(book.getTitle()+" Borrowed by "+member.getName());
			}
		}
	}
	public void findMemberMostBook(){
		Member maxMember = null;
		System.out.println("The member who borrow most book:");
		for(Member member : members.values()){
			if(maxMember == null || member.getBorrowCount() > maxMember.getBorrowCount()){
				maxMember = member;
			}
		}
		if(maxMember != null){
			System.out.println(maxMember.getName());
		}
	}
	public void findMostBorrowBook(){
		Book maxBook = null;
		System.out.println("The Book is most Borrowed:");
		for(Book book: books.values()){
			if(maxBook == null || book.getBorrowCount() > maxBook.getBorrowCount()){
				maxBook = book;
			}
		}
		if(maxBook != null){
			System.out.println(maxBook.getTitle());
		}
	}
	public static void main(String[] args){
		Library lib = new Library();
		String bookId,memberId;
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("\n---------Menu----------");
			System.out.println("1.Add Books");
			System.out.println("2.Add Member");
			System.out.println("3.List of Available books");
			System.out.println("4.Borrow Book");
			System.out.println("5.Return Book");
			System.out.println("6.List of Borrow books");
			System.out.println("7.Member who borrow book most");
			System.out.println("8.The book is most Borrowed");
			System.out.println("9.Exit");
			System.out.println("Enter your choice");
			int choice = sc.nextInt();
			switch(choice){
				case 1:
						sc.nextLine();
						System.out.println("Enter Book ID: ");
       					bookId = sc.nextLine();
						System.out.println("Enter Book Title: ");
						String title = sc.nextLine();
						System.out.println("Enter Book Author: ");
						String author = sc.nextLine();
						Book book = new Book(bookId, title, author);
						lib.addBook(book);
						System.out.println("Book added successfully.");
						break;
				case 2:
						 sc.nextLine();
						 System.out.println("Enter the member id");
						 memberId = sc.nextLine();
						 System.out.println("Enter the member name");
						 String name = sc.nextLine();
						 Member member = new Member(memberId,name);
						 lib.addMember(member);
						 break;
				case 3:
						lib.listAllBooks();
						break;
				case 4:	
						System.out.println("Enter the book id");
						bookId = sc.next();
						System.out.println("Enter the member id");
						memberId = sc.next();
						lib.borrow(bookId,memberId);
						break;
				case 5:
						System.out.println("Enter the book id");
						bookId = sc.next();
						System.out.println("Enter the member id");
						memberId = sc.next();
						lib.returnBook(bookId,memberId);
						break;
				case 6:
						lib.listBorrowBooks();
						break;
				case 7:
						lib.findMemberMostBook();
						break;
				case 8:
						lib.findMostBorrowBook();
						break;
				case 9:
						System.exit(0);
						break;
			}
		}
	}
}
