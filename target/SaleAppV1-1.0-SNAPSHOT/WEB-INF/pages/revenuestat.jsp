<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper" %>

<h2 class="my-4">Thống kê doanh thu tiền thuốc và tiền khám</h2>
<div>
    <button class="btn btn-info" onClick="showYearDropdown('month')">Tháng</button>
    <button class="btn btn-warning" onClick="showYearDropdown('quarter')">Quý</button>
    <button class="btn btn-success" onClick="showYearDropdown('year')">Năm</button>

    <div id="yearDropdown" style="display: none;" >
        <select id="yearSelect" class="form-select w-25 mt-3" onchange="updateStatsRevenue()">
        </select>
    </div>

    <!-- Spinner element -->
    <div id="spinner" style="display: none;">
        <div class="spinner-border" role="status">
            <span class="visually-hidden">Loading...</span>
        </div>
    </div>
    <div id="warning" class="alert alert-danger my-4" role="alert" style="display:none">
      Không có dữ liệu
    </div>
    <canvas id="myChart"></canvas>
</div>
<script>
    // Stat Revenue
    let labels = [];
    let feeRevenue = [];
    let medicineRevenue = [];
    var selectedTime = ''; 

    var updating = false; 

    const showYearDropdown = (time) => {
        document.getElementById('yearDropdown').style.display = 'block';
        selectedTime = time;
        // set the default selected year 
        const yearSelect = document.getElementById('yearSelect');
        const currentYear = new Date().getFullYear();
        yearSelect.value = currentYear;

        // call update stat
        updateStatsRevenue();
    }

    const hideYearDropdown = () => {
        document.getElementById('yearDropdown').style.display = 'none';
    }

    const updateStatsRevenue = () => {
        if (updating) {
            return; 
        }

        updating = true;
        const update = async () => {
            document.getElementById('spinner').style.display = 'block';
            document.getElementById('warning').style.display = 'none';
            
            labels = [];
            feeRevenue = [];
            medicineRevenue = [];

            let preLabel;
            switch (selectedTime) { // Use selectedTime here
                case 'month':
                    preLabel = 'Tháng ';
                    break;
                case 'quarter':
                    preLabel = 'Quý ';
                    break;
                case 'year':
                    preLabel = 'Năm ';
                    break;
                default:
                    preLabel = '';
            }

            const yearSelect = document.getElementById('yearSelect');
            const selectedYear = yearSelect.value;
            
            try {
                const feeRes = await axios.get("/Clinic/api/fee-revenue?time=" 
                        + selectedTime + "&year=" + selectedYear);
                const feeData = feeRes.data;
                const medicineRes = await axios.get("/Clinic/api/medicine-revenue?time="
                        + selectedTime + "&year=" + selectedYear);
                const medicineData = medicineRes.data;

                const feeDataMap = {};
                const medicineDataMap = {};

                for (let i = 0; i < feeData.length; i++) {
                    const key = preLabel + feeData[i][0];
                    const value = feeData[i][1];
                    feeDataMap[key] = value;
                }

                for (let i = 0; i < medicineData.length; i++) {
                    const key = preLabel + medicineData[i][0];
                    const value = medicineData[i][1];
                    medicineDataMap[key] = value;
                }

                if (selectedTime === 'month') {
                    for (let i = 1; i <= 12; i++) {
                        const label = preLabel + i;
                        labels.push(label);
                        feeRevenue.push(feeDataMap[label] || 0);
                        medicineRevenue.push(medicineDataMap[label] || 0);
                    }
                } else if (selectedTime === 'quarter') {
                    for (let i = 1; i <= 4; i++) {
                        const quarterLabel = preLabel + i;
                        labels.push(quarterLabel);
                        feeRevenue.push(feeDataMap[quarterLabel] || 0);
                        medicineRevenue.push(medicineDataMap[quarterLabel] || 0);
                    }
                } else if (selectedTime === 'year') {
                    const label = preLabel + selectedYear;
                    labels.push(label);
                    feeRevenue.push(feeDataMap[label] || 0);
                    medicineRevenue.push(medicineDataMap[label] || 0);
                }
            } catch (error) {
                document.getElementById('warning').style.display = 'block';
                console.error("Error fetching data:", error);
            }
            document.getElementById('spinner').style.display = 'none';

            myChart.data.labels = labels;
            myChart.data.datasets[0].data = feeRevenue;
            myChart.data.datasets[1].data = medicineRevenue;
            myChart.update();

            updating = false; 
        };

        update();
    }

    var ctx2 = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx2, {
        type: 'bar', 
        data: {
            datasets: [{
                type: 'bar',
                label: 'Doanh thu tiền khám',
                data: feeRevenue,
                borderColor: 'rgb(255, 99, 132)',
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
            }, {
                type: 'line',
                label: 'Doanh thu tiền thuốc',
                data: medicineRevenue,
                fill: false,
                borderColor: 'rgb(54, 162, 235)',
            }],
            labels: labels
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    const populateYearOptions = () => {
        const yearSelect = document.getElementById('yearSelect');
        const currentYear = new Date().getFullYear();

        yearSelect.innerHTML = '';

        for (let i = currentYear; i >= currentYear - 5; i--) {
            const option = document.createElement('option');
            option.value = i;
            option.text = i;
            yearSelect.appendChild(option);
        }
    }

    populateYearOptions();
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.5.0/axios.min.js"></script>
