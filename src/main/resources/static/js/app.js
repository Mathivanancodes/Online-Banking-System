// API Base Configuration
const API_BASE = '/api';
let authToken = localStorage.getItem('token');

// Utility: Show Toast Notification
function showToast(message, type = 'success') {
    const container = document.getElementById('toast-container');
    const toast = document.createElement('div');
    toast.className = `toast ${type}`;
    toast.innerHTML = `<i class="fa-solid ${type === 'success' ? 'fa-circle-check' : 'fa-circle-exclamation'}"></i> ${message}`;
    container.appendChild(toast);
    setTimeout(() => { toast.remove(); }, 3000);
}

// Utility: Toggle Views
function showView(viewId) {
    document.querySelectorAll('.view').forEach(el => el.classList.remove('active'));
    document.getElementById(viewId).classList.add('active');
    
    if(viewId === 'dashboard-page') {
        loadDashboardData();
    }
}

function switchDashTab(tabId) {
    document.querySelectorAll('.dash-tab').forEach(el => el.classList.remove('active'));
    document.querySelectorAll('.side-nav a').forEach(el => el.classList.remove('active'));
    
    document.getElementById(`tab-${tabId}`).classList.add('active');
    event.currentTarget.classList.add('active');
}

// Utility: Toggle Auth Modes
function toggleAuthMode(mode) {
    document.querySelectorAll('.auth-toggle button').forEach(b => b.classList.remove('active'));
    document.querySelectorAll('.auth-form').forEach(f => f.classList.remove('active'));
    
    document.getElementById(`btn-${mode}-tab`).classList.add('active');
    document.getElementById(`${mode}-form`).classList.add('active');
}

function togglePassword(inputId) {
    const input = document.getElementById(inputId);
    const icon = event.currentTarget;
    if(input.type === 'password') {
        input.type = 'text';
        icon.classList.replace('fa-eye-slash', 'fa-eye');
    } else {
        input.type = 'password';
        icon.classList.replace('fa-eye', 'fa-eye-slash');
    }
}

// Fetch Wrapper with Auth
async function apiCall(endpoint, method = 'GET', body = null) {
    const headers = { 'Content-Type': 'application/json' };
    if (authToken) headers['Authorization'] = `Bearer ${authToken}`;
    
    const options = { method, headers };
    if (body) options.body = JSON.stringify(body);
    
    try {
        const response = await fetch(`${API_BASE}${endpoint}`, options);
        if(!response.ok) {
            const err = await response.json().catch(()=>({}));
            throw new Error(err.message || `HTTP error! status: ${response.status}`);
        }
        // Handle empty responses
        const text = await response.text();
        return text ? JSON.parse(text) : {};
    } catch (error) {
        showToast(error.message, 'error');
        if(error.message.includes('401') || error.message.includes('403')) {
            handleLogout();
        }
        throw error;
    }
}

// --- Auth Functions ---
async function handleLogin(e) {
    e.preventDefault();
    const email = document.getElementById('login-email').value;
    const password = document.getElementById('login-password').value;
    
    try {
        const data = await apiCall('/auth/login', 'POST', { email, password });
        authToken = data.accessToken;
        localStorage.setItem('token', authToken);
        showToast('Authentication Successful', 'success');
        showView('dashboard-page');
    } catch (error) {
        console.error("Login failed", error);
    }
}

async function handleRegister(e) {
    e.preventDefault();
    const payload = {
        firstName: document.getElementById('reg-firstname').value,
        lastName: document.getElementById('reg-lastname').value,
        email: document.getElementById('reg-email').value,
        phone: document.getElementById('reg-phone').value,
        nationalId: document.getElementById('reg-nationalid').value,
        password: document.getElementById('reg-password').value,
        dateOfBirth: "1990-01-01" // Defaulted for simplicity
    };
    
    try {
        await apiCall('/auth/register', 'POST', payload);
        showToast('Registration complete. Please login.', 'success');
        toggleAuthMode('login');
    } catch (error) {
        console.error("Registration failed", error);
    }
}

function handleLogout() {
    authToken = null;
    localStorage.removeItem('token');
    showView('landing-page');
    showToast('Terminal Disconnected.', 'success');
}

// --- Dashboard Functions ---
async function loadDashboardData() {
    try {
        // Load User Profile
        const profile = await apiCall('/users/profile');
        document.getElementById('user-name-display').innerText = `Welcome, ${profile.firstName || 'Commander'}`;
        
        // Load Accounts
        const accountsData = await apiCall('/accounts?page=0&size=10');
        const accounts = accountsData.content || [];
        
        if (accounts.length > 0) {
            const mainAcc = accounts[0];
            document.getElementById('dash-balance').innerText = `$${mainAcc.balance.toFixed(2)}`;
            
            // Populate Transfer Dropdown
            const select = document.getElementById('tf-source');
            select.innerHTML = '';
            accounts.forEach(acc => {
                const opt = document.createElement('option');
                opt.value = acc.accountNumber;
                opt.text = `${acc.accountType} - ${acc.accountNumber} ($${acc.balance})`;
                select.appendChild(opt);
            });
        } else {
            // Create a default account if none exists
            await apiCall('/accounts', 'POST', { accountType: 'SAVINGS' });
            loadDashboardData(); // Reload
            return;
        }

        // Dummy transactions for visual appeal if API is empty
        renderTransactions([]);
        
    } catch (error) {
        console.error("Error loading dashboard", error);
    }
}

function renderTransactions(txs) {
    // Injecting some dummy futuristic data if actual array is empty
    const dummyTxs = txs.length > 0 ? txs : [
        { id: 1, type: 'DEPOSIT', amount: 5400.00, date: '2026-05-14', desc: 'Payroll Matrix Transfer', status: 'COMPLETED' },
        { id: 2, type: 'WITHDRAWAL', amount: 120.50, date: '2026-05-13', desc: 'Starbucks Orbital Station', status: 'COMPLETED' },
        { id: 3, type: 'TRANSFER', amount: 500.00, date: '2026-05-12', desc: 'To Account ACC84291', status: 'PENDING' }
    ];

    let html = '';
    dummyTxs.forEach(tx => {
        const isPos = tx.type === 'DEPOSIT';
        const sign = isPos ? '+' : '-';
        const cl = isPos ? 'pos' : 'neg';
        const icon = isPos ? 'fa-arrow-down' : 'fa-arrow-up';
        
        let statusCls = 'status-completed';
        if(tx.status === 'PENDING') statusCls = 'status-pending';
        
        html += `
        <div class="tx-item">
            <div class="tx-item-left">
                <div class="tx-icon"><i class="fa-solid ${icon} ${cl}"></i></div>
                <div class="tx-info">
                    <h4>${tx.desc}</h4>
                    <span>${tx.date} • ${tx.type}</span>
                </div>
            </div>
            <div class="tx-amount ${cl}">
                <h4>${sign}$${tx.amount.toFixed(2)}</h4>
                <span class="status-badge ${statusCls}">${tx.status}</span>
            </div>
        </div>
        `;
    });
    
    document.getElementById('recent-transactions-list').innerHTML = html;
    document.getElementById('full-transactions-list').innerHTML = dummyTxs.map(tx => `
        <tr>
            <td>#TXN${Math.floor(Math.random()*100000)}</td>
            <td>${tx.date}</td>
            <td>${tx.desc}</td>
            <td>${tx.type}</td>
            <td class="${tx.type === 'DEPOSIT' ? 'text-success' : ''}">$${tx.amount.toFixed(2)}</td>
            <td><span class="status-badge status-completed">${tx.status}</span></td>
        </tr>
    `).join('');
}

async function handleQuickTransfer(e) {
    e.preventDefault();
    const dest = document.getElementById('qt-account').value;
    const amount = document.getElementById('qt-amount').value;
    const sourceEl = document.getElementById('tf-source');
    if(!sourceEl.options.length) return showToast("No source account", "error");
    
    try {
        await apiCall('/transfers/internal', 'POST', {
            transferType: 'INTERNAL',
            senderAccountNumber: sourceEl.value,
            receiverAccountNumber: dest,
            amount: parseFloat(amount),
            remarks: "Quick Transfer"
        });
        showToast('Hyper-Jump Transfer Successful!', 'success');
        e.target.reset();
        loadDashboardData();
    } catch (error) {
        // handled in wrapper
    }
}

async function handleFullTransfer(e) {
    e.preventDefault();
    try {
        await apiCall('/transfers/internal', 'POST', {
            transferType: 'INTERNAL',
            senderAccountNumber: document.getElementById('tf-source').value,
            receiverAccountNumber: document.getElementById('tf-dest').value,
            amount: parseFloat(document.getElementById('tf-amount').value),
            remarks: document.getElementById('tf-remarks').value
        });
        showToast('Fund Transfer Protocol Executed.', 'success');
        e.target.reset();
        loadDashboardData();
    } catch (error) {}
}

// Initial check
if(authToken) {
    showView('dashboard-page');
}
