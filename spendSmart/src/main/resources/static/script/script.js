        let currentUser = null;
        let expenses = [];
        let splits = [];
        let challenges = [];
        let currentSplit = null;
        let charts = {};

        // Challenge Templates
        const challengeTemplates = [
            { title: "No Online Food Ordering", desc: "Avoid ordering food online this week", days: 7, savings: 1000, icon: "🍔" },
            { title: "Coffee Shop Ban", desc: "Make coffee at home for 5 days", days: 5, savings: 500, icon: "☕" },
            { title: "Public Transport Only", desc: "Use public transport instead of cabs", days: 7, savings: 800, icon: "🚌" },
            { title: "No Shopping Weekend", desc: "Avoid shopping for the weekend", days: 2, savings: 2000, icon: "🛍️" },
            { title: "Pack Your Lunch", desc: "Bring lunch from home this week", days: 5, savings: 600, icon: "🍱" }
        ];

        // Initialize
        document.addEventListener('DOMContentLoaded', () => {
            setupEventListeners();
            const today = new Date().toISOString().split('T')[0];
            document.getElementById('billDate').value = today;
            renderChallengeTemplates();
        });


function showTab(tab) {
  document
    .querySelectorAll(".nav-tab")
    .forEach((t) => t.classList.remove("active"));
  document
    .querySelectorAll(".tab-content")
    .forEach((c) => c.classList.remove("active"));
  event.target.classList.add("active");
  document.getElementById(tab + "Tab").classList.add("active");

  if (tab === "analytics") updateCharts();
  if (tab === "challenges") renderChallenges();
  if (tab === "split") updateBalances();
}

function updateCharts() {
  updateCategoryChart();
  updateTrendChart();
  updateInsights();
}

function updateCategoryChart() {
  const ctx = document.getElementById("categoryChart");
  if (!ctx) return;

  const categoryTotals = {};
  expenses.forEach((exp) => {
    categoryTotals[exp.category] =
      (categoryTotals[exp.category] || 0) + exp.amount;
  });

  if (charts.category) charts.category.destroy();

  charts.category = new Chart(ctx, {
    type: "doughnut",
    data: {
      labels: Object.keys(categoryTotals),
      datasets: [
        {
          data: Object.values(categoryTotals),
          backgroundColor: [
            "#667eea",
            "#764ba2",
            "#f093fb",
            "#f5576c",
            "#4facfe",
            "#00f2fe",
            "#43e97b",
            "#38f9d7",
          ],
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: "bottom",
        },
      },
    },
  });
}

function updateTrendChart() {
  const ctx = document.getElementById("trendChart");
  if (!ctx) return;

  const monthlyData = {};
  const now = new Date();

  for (let i = 5; i >= 0; i--) {
    const date = new Date(now.getFullYear(), now.getMonth() - i, 1);
    const key = date.toLocaleDateString("en-IN", { month: "short" });
    monthlyData[key] = 0;
  }

  expenses.forEach((exp) => {
    const date = new Date(exp.date);
    const key = date.toLocaleDateString("en-IN", { month: "short" });
    if (monthlyData.hasOwnProperty(key)) {
      monthlyData[key] += exp.amount;
    }
  });

  if (charts.trend) charts.trend.destroy();

  charts.trend = new Chart(ctx, {
    type: "line",
    data: {
      labels: Object.keys(monthlyData),
      datasets: [
        {
          label: "Expenses",
          data: Object.values(monthlyData),
          borderColor: "#667eea",
          backgroundColor: "rgba(102, 126, 234, 0.1)",
          tension: 0.4,
          fill: true,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          display: false,
        },
      },
      scales: {
        y: {
          beginAtZero: true,
        },
      },
    },
  });
}

function updateInsights() {
  const total = expenses.reduce((sum, e) => sum + e.amount, 0);
  const avgDaily =
    expenses.length > 0 ? (total / expenses.length).toFixed(2) : 0;
  document.getElementById("avgDaily").textContent = avgDaily;

  const categoryTotals = {};
  expenses.forEach((exp) => {
    categoryTotals[exp.category] =
      (categoryTotals[exp.category] || 0) + exp.amount;
  });

  const topCat = Object.entries(categoryTotals).sort((a, b) => b[1] - a[1])[0];
  document.getElementById("topCategory").textContent = topCat ? topCat[0] : "-";

  const savedAmount = challenges.reduce((sum, c) => sum + c.currentSavings, 0);
  document.getElementById("moneySaved").textContent = savedAmount.toFixed(2);
}

function renderChallenges() {
            const container = document.getElementById('activeChallenges');
            if (!container) return;

            container.innerHTML = '';
            const activeChallenges = challenges.filter(c => c.active);

            if (activeChallenges.length === 0) {
                container.innerHTML = `
                    <div class="empty-state">
                        <div class="empty-state-icon">🎯</div>
                        <h3>No active challenges</h3>
                        <p>Start a challenge to save money!</p>
                    </div>
                `;
                return;
            }

            activeChallenges.forEach(challenge => {
                const progress = (challenge.currentSavings / challenge.targetSavings) * 100;
                const daysLeft = Math.max(0, challenge.duration - Math.floor((Date.now() - new Date(challenge.startDate)) / (1000 * 60 * 60 * 24)));

                const card = document.createElement('div');
                card.className = 'challenge-card';
                card.innerHTML = `
                    <div class="challenge-header">
                        <div class="challenge-title">${challenge.icon || '🎯'} ${challenge.title}</div>
                        <div class="challenge-icon">${daysLeft} days</div>
                    </div>
                    <div class="challenge-description">${challenge.description}</div>
                    <div class="challenge-progress">
                        <div class="challenge-progress-bar" style="width: ${Math.min(progress, 100)}%"></div>
                    </div>
                    <div class="challenge-stats">
                        <span>₹${challenge.currentSavings} / ₹${challenge.targetSavings}</span>
                        <span>${Math.round(progress)}% Complete</span>
                    </div>
                    <div class="challenge-actions">
                        <button class="btn btn-small btn-success" onclick="updateChallengeProgress(${challenge.id}, 100)">+₹100</button>
                        <button class="btn btn-small btn-secondary" onclick="completeChallenge(${challenge.id})">Complete</button>
                    </div>
                `;
                container.appendChild(card);
            });
        }
