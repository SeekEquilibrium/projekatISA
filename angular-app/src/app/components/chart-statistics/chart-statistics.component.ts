import { Component, Input, OnInit } from "@angular/core";
import { Chart } from "node_modules/chart.js";
@Component({
    selector: "app-chart-statistics",
    templateUrl: "./chart-statistics.component.html",
    styleUrls: ["./chart-statistics.component.css"],
})
export class ChartStatisticsComponent implements OnInit {
    @Input() stats: any;
    yearly = [
        [1, "January"],
        [2, "February"],
        [3, "March"],
        [4, "April"],
        [5, "May"],
        [6, "June"],
        [7, "July"],
        [8, "August"],
        [9, "September"],
        [10, "October"],
        [11, "November"],
        [12, "December"],
    ];
    lastFiveYears = [];
    now = new Date();
    constructor() {
        this.now.getFullYear();
        for (let i = 0; i < 5; i++) {
            this.lastFiveYears.unshift([this.now.getFullYear() - i, 0]);
        }
    }

    ngOnInit() {
        // console.log("STATS", this.stats);
        for (let i = 0; i < 12 - (this.now.getMonth() + 1); i++) {
            this.yearly.unshift(this.yearly.pop());
        }
        // console.log(this.yearly);
        this.thisYearRevenue();
        this.thisYearVisits();
        this.lastFiveYearsRevenue();
        this.lastFiveYearsVisits();
    }

    private lastFiveYearsVisits() {
        let revenue = this.lastFiveYears.map(function (arr) {
            return arr.slice();
        });
        this.stats.visitsByYears.map((x) => {
            for (let j = 0; j < revenue.length; j++) {
                if (revenue[j][0] == x.year) {
                    console.log(revenue[j][1]);
                    revenue[j][1] = x.numberOfDays;
                }
            }
        });
        return new Chart("lastFiveYearsVisits", {
            type: "bar",
            data: {
                labels: revenue.map((x) => x[0]),
                datasets: [
                    {
                        label: "Number of occupied days",
                        data: revenue.map((x) => x[1]),
                        backgroundColor: "rgba(104,60,180, 0.5)",
                        borderColor: "rgba(104,60,180, 1)",
                        borderWidth: 2,
                    },
                ],
            },
            options: {
                responsive: false,

                scales: {
                    y: {
                        beginAtZero: true,
                    },
                },
            },
        });
    }

    private lastFiveYearsRevenue() {
        let revenue = this.lastFiveYears.map(function (arr) {
            return arr.slice();
        });
        // visits[2020][1] = 23;
        this.stats.revanueByYears.map((x) => {
            for (let j = 0; j < revenue.length; j++) {
                if (revenue[j][0] == x.year) {
                    console.log(revenue[j][1]);
                    revenue[j][1] = x.revenue;
                }
            }
        });
        return new Chart("lastFiveYearsRevenue", {
            type: "bar",
            data: {
                labels: revenue.map((x) => x[0]),
                datasets: [
                    {
                        label: "Revenue of the year",
                        data: revenue.map((x) => x[1]),
                        backgroundColor: "rgba(104,60,180, 0.5)",
                        borderColor: "rgba(104,60,180, 1)",
                        borderWidth: 2,
                    },
                ],
            },
            options: {
                responsive: false,

                scales: {
                    y: {
                        beginAtZero: true,
                    },
                },
            },
        });
    }

    private thisYearVisits() {
        let visits = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
        this.stats.visitsByMonths.map((x) => [
            (visits[x.month] = x.numberOfDays),
        ]);
        for (let i = 0; i < 11 - (this.now.getMonth() + 1); i++) {
            visits.unshift(visits.pop());
        }
        return new Chart("yearlyVisits", {
            type: "bar",
            data: {
                labels: this.yearly.map((x) => x[1]),
                datasets: [
                    {
                        label: "Number of occupied days",
                        data: visits,
                        backgroundColor: "rgba(104,60,180, 0.5)",
                        borderColor: "rgba(104,60,180, 1)",
                        borderWidth: 2,
                    },
                ],
            },
            options: {
                responsive: false,

                scales: {
                    y: {
                        beginAtZero: true,
                    },
                },
            },
        });
    }

    private thisYearRevenue() {
        let revenue = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
        this.stats.revanueByMonths.map((x) => [(revenue[x.month] = x.revenue)]);
        for (let i = 0; i < 11 - (this.now.getMonth() + 1); i++) {
            revenue.unshift(revenue.pop());
        }
        return new Chart("yearlyRevanue", {
            type: "bar",
            data: {
                labels: this.yearly.map((x) => x[1]),
                datasets: [
                    {
                        label: "Revenue of the month",
                        data: revenue,
                        backgroundColor: "rgba(104,60,180, 0.5)",
                        borderColor: "rgba(104,60,180, 1)",
                        borderWidth: 2,
                    },
                ],
            },
            options: {
                responsive: false,
                scales: {
                    y: {
                        beginAtZero: true,
                    },
                },
            },
        });
    }
}
