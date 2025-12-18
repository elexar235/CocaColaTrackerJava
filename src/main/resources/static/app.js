const API_URL = 'http://localhost:8080';

// Initialize
document.addEventListener('DOMContentLoaded', () => {
    loadUsers();
    loadLeaderboard();
});

// Forms
document.getElementById('addUserForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const data = {
        username: document.getElementById('username').value,
        email: document.getElementById('email').value
    };

    try {
        const response = await fetch(`${API_URL}/users`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });
        if (response.ok) {
            alert('User created!');
            document.getElementById('addUserForm').reset();
            loadUsers();
        } else {
            alert('Error creating user');
        }
    } catch (err) {
        console.error(err);
    }
});

document.getElementById('addConsumptionForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const data = {
        userId: document.getElementById('userId').value,
        amountMl: document.getElementById('amount').value
    };

    try {
        const response = await fetch(`${API_URL}/consumptions`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });
        if (response.ok) {
            alert('Glug glug glug! 🥤');
            document.getElementById('addConsumptionForm').reset();
            loadLeaderboard();
        } else {
            alert('Error adding drink (User ID might be wrong)');
        }
    } catch (err) {
        console.error(err);
    }
});

// Loaders
async function loadUsers() {
    const list = document.getElementById('usersList');
    list.innerHTML = '<p>Loading...</p>';

    try {
        const res = await fetch(`${API_URL}/users`);
        const users = await res.json();

        list.innerHTML = users.map(user => `
            <div class="user-item">
                <span class="id-badge">ID: ${user.id}</span>
                <h3>${user.username}</h3>
                <p>${user.email}</p>
                
                <div class="achievements">
                    ${user.achievements.map(ach => `<span class="achievement-badge">🏅 ${ach}</span>`).join('')}
                </div>

                <button onclick="deleteUser(${user.id})" class="btn-danger">Delete 🗑️</button>
            </div>
        `).join('');
    } catch (err) {
        list.innerHTML = '<p class="error">Failed to load users</p>';
    }
}

async function deleteUser(id) {
    if (!confirm('Are you sure you want to delete this cola lover?')) return;

    try {
        const response = await fetch(`${API_URL}/users/${id}`, {
            method: 'DELETE'
        });
        if (response.ok) {
            loadUsers();
            loadLeaderboard();
        } else {
            alert('Failed to delete user');
        }
    } catch (err) {
        console.error(err);
        alert('Error deleting user');
    }
}

async function loadLeaderboard() {
    const tbody = document.querySelector('#leaderboardTable tbody');
    tbody.innerHTML = '<tr><td colspan="3">Loading...</td></tr>';

    try {
        const res = await fetch(`${API_URL}/consumptions/stats/leaderboard`);
        const stats = await res.json();

        if (stats.length === 0) {
            tbody.innerHTML = '<tr><td colspan="3">No drinks yet. Be the first!</td></tr>';
            return;
        }

        tbody.innerHTML = stats.map((stat, index) => `
            <tr>
                <td>${index + 1}${index === 0 ? ' 👑' : ''}</td>
                <td>${stat.username}</td>
                <td>${(stat.totalAmountMl / 1000).toFixed(2)} L</td>
            </tr>
        `).join('');
    } catch (err) {
        tbody.innerHTML = '<tr><td colspan="3">Failed to load leaderboard</td></tr>';
    }
}
