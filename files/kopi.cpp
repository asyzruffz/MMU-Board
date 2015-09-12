/*****************************************************************
Program: TC103_14T1_ASG1_Muhammad_Asyraf_bin_Ibrahim_program2.cpp
Course: TCP 1101
Year: 2014/15 Trimester 1
Name: Muhammad Asyraf bin Ibrahim
ID: 1141123928
Lecture: TC103
Lab: TC205
Email: asyzruffz@gmail.com
Phone: 013-4642899
*****************************************************************/

#include <iostream>
using namespace std;

void showMenu();
bool askUser();	//function for a (yes/no) question
void displayOrder(int, int, int, int, int);
int extra_milksugar(int, int);

int main()
{
	int choice, quantity;
	double price;
	bool adjustment, moreOrder;
	
	int expresso = 0, cappucino = 0, latte = 0, hotchocolate = 0, tea = 0, milk_sugar = 0;
	int expPlus = 0, capPlus = 0, latPlus = 0, hchocPlus = 0, teaPlus = 0;
	
	do	//loop until no more adjustment to be made
	{
		do	//add or delete an order
		{
			showMenu();
			
			cout << "Please choose your drinks and quantity (separated by space): ";
			cin >> choice >> quantity;
			
			switch (choice)
			{
				case 1:
					milk_sugar = milk_sugar + extra_milksugar(quantity, expPlus);
					expresso = expresso + quantity;
					break;
				case 2:
					milk_sugar = milk_sugar + extra_milksugar(quantity, capPlus);
					cappucino = cappucino + quantity;
					break;
				case 3:
					milk_sugar = milk_sugar + extra_milksugar(quantity, latPlus);
					latte = latte + quantity;
					break;
				case 4:
					milk_sugar = milk_sugar + extra_milksugar(quantity, hchocPlus);
					hotchocolate = hotchocolate + quantity;
					break;
				case 5:
					milk_sugar = milk_sugar + extra_milksugar(quantity, teaPlus);
					tea = tea + quantity;
					break;
				default:
					cout << "Error! Wrong input." << endl;
					cout << endl;
			}
			
			cout << "Do you want to add more drinks? (y/n) ";
			moreOrder = askUser();
			cout << endl;
			
		} while(moreOrder);
		
		displayOrder(expresso, cappucino, latte, hotchocolate, tea);
		
		cout << "Do you want to make any change to your order? (y/n) ";
		adjustment = askUser();
		if(adjustment)
			cout << "Enter negative quantity to delete." << endl;
		cout << endl;
	} while(adjustment);
	
	price = 0.5 * milk_sugar + 2.5 * expresso + 2.5 * cappucino + 2.5 * latte + 2.5 * hotchocolate + 2.5 * tea;
	
	cout << "Total price: RM" << price << endl;
	
	return 0;
}

void showMenu()
{
	cout << " +---+---------------+------+" << endl;
	cout << " |No.|     Item      |  RM  |" << endl;
	cout << " +---+---------------+------+" << endl;
	cout << " | 1 | Expresso      | 2.50 |" << endl;
	cout << " +---+---------------+------+" << endl;
	cout << " | 2 | Cappucino     | 2.50 |" << endl;
	cout << " +---+---------------+------+" << endl;
	cout << " | 3 | Latte         | 2.50 |" << endl;
	cout << " +---+---------------+------+" << endl;
	cout << " | 4 | Hot Chocolate | 2.00 |" << endl;
	cout << " +---+---------------+------+" << endl;
	cout << " | 5 | Plain Tea     | 1.00 |" << endl;
	cout << " +---+---------------+------+" << endl;
}

bool askUser()
{
	char answer;
	
	cin >> answer;
	if(answer == 'y')
		return true;
	else
		return false;
}

void displayOrder(int expresso, int cappucino, int latte, int hotchocolate, int tea)
{
	if(expresso > 0 || cappucino > 0 || latte > 0 || hotchocolate > 0 || tea > 0)
	{
		cout << "You have ordered:" << endl;
	
		if(expresso > 0)
			cout << "  " << expresso << " expresso." << endl;
		if(cappucino > 0)
			cout << "  " << cappucino << " cappucino." << endl;
		if(latte > 0)
			cout << "  " << latte << " latte." << endl;
		if(hotchocolate > 0)
			cout << "  " << hot chocolate << " hotchocolate." << endl;
		if(tea > 0)
			cout << "  " << tea << " tea." << endl << endl;
		
		cout << endl;
	}
	else
		cout << "You have ordered nothing." << endl << endl;
}

int extra_milksugar(int quantity, int drinkPlus)
{
	int extra = 0;
	
	if(quantity < 0)
		return quantity * drinkPlus;
	
	cout << "Do you want to add extra milk? (y/n) ";
	if( askUser() )
		extra = extra + quantity;
	cout << "Do you want to add extra sugar? (y/n) ";
	if( askUser() )
		extra = extra + quantity;
	cout << endl;
	
	return extra;
}
