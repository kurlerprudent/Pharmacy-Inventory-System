

# 📘 Pharmacy Inventory System Documentation

## 📖 Overview

This console-based **Pharmacy Inventory System** efficiently manages drug inventory, sales, and reporting using optimized data structures and algorithms. It handles all core pharmacy operations while maintaining data persistence through CSV files.

---

## 📊 Data Structures Used

| Data Structure   | Purpose                                        | Time Complexity             |
| ---------------- | ---------------------------------------------- | --------------------------- |
| **HashMap**      | O(1) access to drugs/suppliers/customers by ID | Get/Put: O(1) avg           |
| **Min-Heap**     | Track low-stock items (priority queue)         | Insert: O(log n), Min: O(1) |
| **Queue (FIFO)** | Maintain full transaction history in order     | Enqueue/Dequeue: O(1)       |
| **Stack (LIFO)** | Track recent transactions                      | Push/Pop: O(1)              |
| **HashSet**      | Maintain unique drug-supplier relationships    | Add/Contains: O(1) avg      |
| **ArrayList**    | Dynamic arrays for sorting/searching           | Access: O(1), Insert: O(n)  |

---

## 🧠 Algorithms Used

| Algorithm          | Purpose                                             | Time       | Space |
| ------------------ | --------------------------------------------------- | ---------- | ----- |
| **Merge Sort**     | Sort drugs by price                                 | O(n log n) | O(n)  |
| **Insertion Sort** | Sort drugs by expiry (small/partially sorted lists) | O(n²)      | O(1)  |
| **Binary Search**  | Find drugs by code (sorted lists)                   | O(log n)   | O(1)  |
| **Linear Search**  | Find drugs by name or supplier                      | O(n)       | O(1)  |
| **Heap Ops**       | Maintain low-stock alerting                         | O(log n)   | O(1)  |

---

## 🗂️ File Structure and Responsibilities

### Core

* **`Main.java`**: Launches app, calls `MenuSystem`

### Models (`models/`)

* **`Drug.java`**: Drug info (code, name, price, expiry, etc.)
* **`Supplier.java`**: Supplier info (ID, name, contact)
* **`Customer.java`**: Customer details
* **`Transaction.java`**: Sales record (drug, customer, date)

### Storage (`storage/`)

* **`DrugStorage.java`**: Loads/saves `drugs.csv`, manages low stock
* **`SupplierStorage.java`**: Manages `suppliers.csv`
* **`CustomerStorage.java`**: Manages `customers.csv`
* **`TransactionStorage.java`**: Records to `transactions.csv`, uses Queue & Stack

### Logic (`logic/`)

* **`InventoryManager.java`**: Core logic (add, validate, update stock)
* **`SearchService.java`**: Linear & binary search
* **`SortService.java`**: Merge & insertion sort
* **`StockMonitor.java`**: Alerts for low stock & expiry

### UI (`ui/`)

* **`DrugMenu.java`**: Add/search/view drugs
* **`SalesMenu.java`**: Record sales, show history
* **`ReportMenu.java`**: Reports (stock, expiry, sales)
* **`ConsoleHelper.java`**: Input/output formatting with color

### Utilities (`utils/`)

* **`CSVUtils.java`**: Read/write CSVs
* **`DateUtils.java`**: Handle/validate dates
* **`ValidationUtils.java`**: Input checks
* **`CustomHeap.java`**: Min-heap implementation
* **`CustomQueue.java`**: FIFO implementation
* **`CustomStack.java`**: LIFO implementation

---

## 🔄 System Workflow

1. `Main.java` calls `MenuSystem`
2. Menu routes to:

   * `DrugMenu` (for inventory)
   * `SalesMenu` (for transactions)
   * `ReportMenu` (for analytics)
3. Logic layers handle operations
4. Storage updates CSV via `CSVUtils`
5. Data structures (Map, Heap, Stack) manage memory

---

## 💡 Key Technical Decisions

* **HashMap** for fast access to key data
* **Min-Heap** for real-time low-stock alerts
* **Merge Sort** for efficient price sorting
* **Queue + Stack** for robust sales tracking
* **CSV** for simple, portable storage

---

## ⚙️ Performance Characteristics

| Operation       | Time       | Space | Structure          |
| --------------- | ---------- | ----- | ------------------ |
| Add Drug        | O(log n)   | O(1)  | HashMap + Min-Heap |
| Search by ID    | O(1)       | O(1)  | HashMap            |
| Search by Name  | O(n)       | O(1)  | Linear             |
| Sort by Price   | O(n log n) | O(n)  | Merge Sort         |
| Sort by Expiry  | O(n²)      | O(1)  | Insertion Sort     |
| Record Sale     | O(1)       | O(1)  | Queue/Stack        |
| Low-Stock Alert | O(1)       | O(1)  | Heap               |
| Sales Report    | O(n)       | O(1)  | Queue traversal    |

---

## ▶️ How to Run

### Requirements:

* Java 11+
* Terminal with ANSI color support

### Directory Layout:

```
pharmacy-inventory-system/
├── data/
│   ├── drugs.csv
│   ├── suppliers.csv
│   ├── customers.csv
│   └── transactions.csv
└── src/com/pharmacy/
    └── Main.java + all files
```

### Compile:

```bash
javac -d bin src/com/pharmacy/**/*.java
```

### Run:

```bash
java -cp bin com.pharmacy.Main
```

---

## 🧪 Sample CSV

**drugs.csv**:

```csv
code,name,quantity,price,expiry,supplier_id
D001,Paracetamol,100,5.99,2024-12-31,S001
```

---

## ✅ Conclusion

This system provides:

* ✅ Fast operations with HashMaps and Heaps
* ✅ Smart sorting for pricing and expiry tracking
* ✅ Dual-layer sales history via Stack + Queue
* ✅ Human-readable, portable CSV storage
* ✅ Clean console interface with validation

It’s a reliable, efficient solution for pharmacy inventory management, blending simplicity with strong technical foundations.


