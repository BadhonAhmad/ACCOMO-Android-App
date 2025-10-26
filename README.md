🏠 ACCOMO – Smart Resident Management System
ACCOMO is a modern Android application designed to simplify the management of rented residences.
It bridges the communication gap between owners and tenants, automating rent, bills, notifications, and complaints — all in one elegant platform.

🌟 Features
👨‍💼 For Owners

🔑 Flat Access Code: Generate and share a unique code for each tenant to access their specific flat.
💸 Bill Management: Notify tenants about unpaid bills or monthly rent directly through the app.
🗓️ Monthly Notifications: Automatically send reminders for each new month's payment cycle.
🧾 Track Payments: View all tenants' payment histories and pending dues in one dashboard.

👨‍💻 For Tenants

🏠 Flat Overview: View assigned flat details, due bills, and payment status.
📩 Receive Notifications: Get instant updates on rent, utility bills, and maintenance issues.
🧾 Pay Online: Securely pay bills via bKash or other integrated payment gateways (demo integration available).
📢 Submit Complaints: Directly raise complaints or issues to the owner with real-time updates.


🧠 Core Concept
ACCOMO digitalizes the tenant-owner relationship by automating communication, billing, and issue tracking.
It removes manual effort in managing payments and helps both sides maintain transparency and accountability.

🛠️ Tech Stack
LayerTechnologyFrontendKotlin + Jetpack ComposeBackendMySQL (RESTful API integrated)ArchitectureMVVM (Model–View–ViewModel)Payment IntegrationDemo bKash Gateway / Custom Payment GatewayNotificationsFirebase Cloud Messaging (FCM) (Optional)

📱 App Workflow

Owner Registration & Flat Setup

Owner creates a flat profile and generates a unique entry code for each tenant.


Tenant Registration & Flat Joining

Tenant joins using the unique code provided by the owner.


Bill & Notification System

Owner can add or update bills and push notifications for pending payments.
Automatic reminders are sent at the start of each month.


Payment Processing

Tenants can pay via integrated demo payment gateways (e.g., bKash).


Complaint Management

Tenants can submit complaints; owners can respond and mark them as resolved.




📂 Project Structure
ACCOMO/
│
├── app/
│   ├── ui/           # Jetpack Compose screens (Home, Login, Dashboard, etc.)
│   ├── viewmodel/    # ViewModel classes (MVVM architecture)
│   ├── repository/   # Data handling and API integration
│   ├── model/        # Data models (User, Flat, Bill, Complaint)
│   ├── utils/        # Common utilities and helpers
│   └── network/      # Backend communication setup
│
└── backend/
    ├── database/     # MySQL database schema and scripts
    ├── api/          # REST API endpoints (Node/Java/PHP layer)
    └── config/       # Connection and environment setup

🧾 Example Use Case

The owner adds a new flat with ID: F102 and shares the join code: ACCM102.
The tenant opens ACCOMO → Enters code ACCM102 → Gets access to their flat dashboard.
The owner sends a notification:
"Your water bill (৳500) for October is unpaid."
The tenant receives it instantly and pays via bKash demo gateway.
Both sides see the updated payment status in real time.


💳 Payment Gateway Integration

Supports Demo bKash Payment integration for testing.
Can be extended with SSLCommerz, Stripe, or Razorpay.
Uses secure transaction verification for each payment.


⚙️ Installation & Setup
📍 Prerequisites

Android Studio (latest version)
MySQL Server running locally or in the cloud
Backend API URL configured in constants.kt

🚀 Steps
bash# 1️⃣ Clone this repository
git clone https://github.com/yourusername/ACCOMO.git

# 2️⃣ Open in Android Studio
# 3️⃣ Configure API endpoint in constants.kt
# 4️⃣ Build and Run on Emulator or Physical Device

🧩 Future Enhancements

🔐 Role-based authentication (Owner / Tenant)
📊 Analytics for payment history
🧠 AI-based rent prediction
🌐 Cloud database integration with Firebase or AWS RDS


👨‍💻 Author
Nobel Ahmad Badhon
📧 ahmadbadhon28@gmail.com
🌐 GitHub: BadhonAhmad
💼 LinkedIn
