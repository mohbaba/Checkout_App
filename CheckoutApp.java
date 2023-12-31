import java.util.*;

public class CheckoutApp{
	
	private static ArrayList<String> products = new ArrayList<String>();
	private static ArrayList<Integer> productQuantity = new ArrayList<Integer>();
	private static ArrayList<Double> prices = new ArrayList<Double>();
	private static ArrayList<Double> priceTotal = new ArrayList<Double>();
	private static Date date = new Date();
	private static Scanner scanner = new Scanner(System.in);
	private static String continuePrompt = "no";
	private static double vat = 7.5;
	private static double pricePaid;
	private static double discount;
	
	public static void cashierPrompts(){
		
		System.out.println("What did the user buy?");
		String productName = scanner.next();

		products.add(productName);

		System.out.println("How many pieces?");
		int quantity = scanner.nextInt();
		
		productQuantity.add(quantity);

		System.out.println("How much per unit?");
		double productPrice = scanner.nextDouble();

		prices.add(productPrice);

		
	


	}


	public static ArrayList<Double> getPriceTotal(){
		
		for(int i = 0; i < prices.size() ; i++){
			priceTotal.add(productQuantity.get(i) * prices.get(i));
		}
		
		return priceTotal;

	}
	public static double calculateTotal(){
		
		double total = 0;
		for(int i = 0; i < prices.size() ; i++){
			
			total += priceTotal.get(i);
		}

		return total;
	}


	public static double discountTotal(double discount){

		return calculateTotal() * (discount/100);
		
	}

	public static double vatDiscount(){

		return calculateTotal() * (vat/100);
	} 

	public static boolean paymentAmountCheck(){

		boolean check = false;
		if(pricePaid > ((calculateTotal()+vatDiscount()) - discountTotal(discount))){
			check = true;
		}
		return check;
	}

	
	public static void itemListDisplay(){

		for(int i = 0 ; i < products.size() ; i++){

			System.out.printf("%20s%10s%10s%15s%n", products.get(i),productQuantity.get(i),prices.get(i),getPriceTotal().get(i));
		}
	}
 
	public static void receipt(){
		
		System.out.printf("%40s: %.2f%n","Amount Paid", pricePaid);
		System.out.printf("%40s: %.2f%n","Balance", pricePaid - billTotal());
		System.out.println("--------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------");
		System.out.println("\t\t\tTHANK YOU FOR YOUR PATRONAGE");

		System.out.println("--------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------");
	}

	public static double billTotal(){

		return (calculateTotal()+vatDiscount()) - discountTotal(discount);
	}

	public static void paymentLine(){


		System.out.println("--------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------");
		System.out.printf("THIS IS NOT AN RECEIPT KINDLY PAY %.2f%n",billTotal());

		System.out.println("--------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------");

	}
	
	public static void displayPaymentSlip(String cashierName, String customerName, double discount){

		System.out.print("SEMICOLON STORES\nMAIN BRANCH\nLOCATION: 312, HERBERT MACAULAY WAY, SABO YABA, LAGOS.\nTEL: 03293828343\n");
		System.out.printf("Date: %s%n",date);
		System.out.printf("Cashier: %s%n",cashierName);
		System.out.printf("Customer Name: %s%n",customerName);
		System.out.println("--------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------");

		System.out.printf("%20s%10s%10s%15s%n","ITEM","QTY","PRICE","TOTAL(NGN)");
		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		itemListDisplay();
		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.printf("%40s: %.2f%n","Sub Total",calculateTotal());
		System.out.printf("%40s: %.2f%n","Discount",discountTotal(discount));
		System.out.printf("%40s: %.2f%n","VAT @ 7.50%", vatDiscount());
		System.out.println("--------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------");

		System.out.printf("%40s: %.2f%n","Bill Total", billTotal());

		
		
		

	}

	public static void main(String[] args){
		
		System.out.println("What is the customer's name? ");
		String customerName = scanner.next();

		do{
			cashierPrompts();
			System.out.println("Add more Items?");
			continuePrompt = scanner.next().toLowerCase();

		}
		while(continuePrompt.equals("yes"));
		
		
		System.out.println("What is your name?");
		String cashierName = scanner.next();
		System.out.println("How much discount will the customer get?");
		discount = scanner.nextDouble();
		System.out.println("");
		
		displayPaymentSlip(cashierName,customerName,discount);
		paymentLine();
		System.out.println("\n\n\n\n");
		
		System.out.println("How much did the customer give to you?");
		pricePaid = scanner.nextDouble();
		while(paymentAmountCheck() != true){
				System.out.println("Pay the correct amount");
				pricePaid = scanner.nextDouble();
				System.out.println("");
			
				if(paymentAmountCheck() == true){
					displayPaymentSlip(cashierName, customerName,discount);
					receipt();
					
				}
			}


	}

}