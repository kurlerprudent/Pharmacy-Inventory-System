# 🏥 Pharmacy Inventory System

Terminal-based inventory management for Atinka Meds pharmacy  
*DCIT308 Group Project - Semester 2, 2024/2025*

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- Git (for team collaboration)

### Installation
1. Clone the repository:
```bash
git clone https://github.com/your-username/pharmacy-inventory-system.git



pharmacy-inventory-system/
├── src/
│   └── com/
│       └── pharmacy/
│           ├── Main.java
│           ├── models/
│           │   ├── Drug.java
│           │   ├── Supplier.java
│           │   ├── Customer.java
│           │   └── Transaction.java
│           ├── storage/
│           │   ├── DrugStorage.java
│           │   ├── SupplierStorage.java
│           │   └── TransactionStorage.java
│           ├── logic/
│           │   ├── InventoryManager.java
│           │   ├── SearchService.java
│           │   ├── SortService.java
│           │   └── StockMonitor.java
│           ├── ui/
│           │   ├── MenuSystem.java
│           │   ├── DrugMenu.java
│           │   ├── ReportMenu.java
│           │   └── ConsoleHelper.java
│           └── utils/
│               ├── DateUtils.java
│               ├── ValidationUtils.java
│               ├── CustomQueue.java
│               ├── CustomStack.java
│               └── CustomHeap.java
├── data/
│   ├── drugs.csv
│   ├── suppliers.csv
│   ├── customers.csv
│   └── transactions.csv
├── config.properties
└── README.md


1. models/ - Data Structures
Define all entities as Java classes:
Drug.java: Medicine properties (code, name, quantity, price, expiry)
Supplier.java: Supplier information
Customer.java: Customer profiles
Transaction.java: Purchase records

2. storage/ - File Handling
Implement CSV file operations:
DrugStorage.java: Load/save drugs
SupplierStorage.java: Handle supplier data

TransactionStorage.java: Manage purchase history
Key responsibilities:
Read CSV files on startup
Convert CSV rows to Java objects
Save objects back to CSV
Manual CSV parsing (no libraries!)

3. logic/ - Business Rules
Contains core functionality:
InventoryManager.java: Add/remove/update drugs
SearchService.java: Implement search algorithms
SortService.java: Custom sorting logic
StockMonitor.java: Low stock alerts

Must implement manually:
Linear/binary search
Insertion/merge sort
Queue for purchase history
Stack for sales tracking

4. ui/ - User Interface
Handle all user interactions:
MenuSystem.java: Main menu navigation
DrugMenu.java: Drug management screens
ReportMenu.java: Reporting interface
ConsoleHelper.java: Colored output helpers

5. utils/ - Helper Classes
Reusable utilities:
DateUtils.java: Date conversions
ValidationUtils.java: Input validation
CustomQueue.java: Purchase history
CustomStack.java: Sales tracking
CustomHeap.java: Stock alerts