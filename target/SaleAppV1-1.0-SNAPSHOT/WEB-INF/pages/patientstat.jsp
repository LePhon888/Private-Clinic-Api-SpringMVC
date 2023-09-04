<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper" %>

<h2 class="my-4">Thống kê số lượng bệnh nhân</h2>
<div>
    <button class="btn btn-info" onClick="showYearDropdown('month')">Tháng</button>
    <button class="btn btn-warning" onClick="showYearDropdown('quarter')">Quý</button>
    <button class="btn btn-success" onClick="showYearDropdown('year')">Năm</button>

    <div id="yearDropdown" style="display: none;" >
        <select id="yearSelect" class="form-select w-25 mt-3" onchange="updatePatientStat()">
        </select>
    </div>

    <div id="spinner" style="display: none;">
        <div class="spinner-border" role="status">
            <span class="visually-hidden">Loading...</span>
        </div>
    </div>
    <canvas id="myChart"></canvas>
</div>
<br/>
<script>
    var labels = [];
    var values = [];
    var selectedTime = ''; 

    // multiple columns being added to the chart
    var updating = false; // Flag to track if an update is in progress

    const showYearDropdown = (time) => {
        document.getElementById('yearDropdown').style.display = 'block';
        selectedTime = time; // Store the selected time interval
          // set the default selected year 
        const yearSelect = document.getElementById('yearSelect');
        const currentYear = new Date().getFullYear();
        yearSelect.value = currentYear;

        // call update stat
        updatePatientStat();
    }

    const hideYearDropdown = () => {
        document.getElementById('yearDropdown').style.display = 'none';
    }

    const updatePatientStat = async () => {
        if (updating) {
            return; 
        }

        updating = true;
        document.getElementById('spinner').style.display = 'block';

        labels = [];
        values = [];

        let preLabel;
        switch (selectedTime) { 
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

        let fetchDataUrl = "/Clinic/api/count-patient?time=" + selectedTime;
        if (selectedYear) {
            fetchDataUrl += "&year=" + selectedYear;
        }

        const res = await axios.get(fetchDataUrl);
        const data = res.data;
        
        console.log(selectedTime);
        console.log(res.data);

        
        const dataMap = {};

        for (let i = 0; i < data.length; i++) {
            const key = preLabel + data[i][0];
            const value = data[i][1];
            dataMap[key] = value;
        }

        if (selectedTime === 'month') {
            for (let i = 1; i <= 12; i++) {
                const monthLabel = preLabel + i;
                labels.push(monthLabel);
                values.push(dataMap[monthLabel] || 0);
            }
        } else if (selectedTime === 'quarter') {
            for (let i = 1; i <= 4; i++) {
                const quarterLabel = preLabel + i;
                labels.push(quarterLabel);
                values.push(dataMap[quarterLabel] || 0); 
            }
        } else {
            labels.push(preLabel + selectedYear);
            values.push(dataMap[preLabel + selectedYear] || 0);
        }

        document.getElementById('spinner').style.display = 'none';

        myChart.data.labels = labels;
        myChart.data.datasets[0].data = values;
        myChart.update();

        updating = false; 
    }

    var ctx1 = document.getElementById('myChart').getContext('2d');

    var myChart = new Chart(ctx1, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Số lượng bệnh nhân đến khám',
                data: values,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
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
