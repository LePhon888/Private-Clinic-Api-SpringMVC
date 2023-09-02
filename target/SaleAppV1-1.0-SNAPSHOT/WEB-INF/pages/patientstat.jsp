<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper" %>

<h2>Thống kê số lượng bệnh nhân</h2>
<div>
    <button class="btn btn-info" onClick="updatePatientStat('month')">Tháng</button>
    <button class="btn btn-warning" onClick="updatePatientStat('quarter')">Quý</button>
    <button class="btn btn-success" onClick="updatePatientStat('year')">Năm</button>

    <!-- Spinner element -->
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
    
    // multiple columns being added to the chart
    var updating = false; // Flag to track if an update is in progress
    //Count Patient Stat
    const updatePatientStat = (time) => {
        
        if (updating) {
            return; 
        }

        updating = true;
        const update = async () => {
        document.getElementById('spinner').style.display = 'block';

        labels = [];
        values = [];
        
        let preLabel;
        switch (time) {
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
                preLabel = ''; // Default label if time is not recognized
        }

        const res = await axios.get("/Clinic/api/count-patient?time="+time);
        const data = res.data;
        
       const dataMap = {};

        for (let i = 0; i < data.length; i++) {
            const key = preLabel + data[i][0];
            const value = data[i][1];
            dataMap[key] = value;
        }

        if (time === 'month') {
            for (let i = 1; i <= 12; i++) {
                const monthLabel = preLabel + i;
                labels.push(monthLabel);
                values.push(dataMap[monthLabel] || 0);
            }
        } else if (time === 'quarter') {
            for (let i = 1; i <= 4; i++) {
                const quarterLabel = preLabel + i;
                labels.push(quarterLabel);
                values.push(dataMap[quarterLabel] || 0); 
            }
        } else {
            labels.push(preLabel + new Date().getFullYear());
            values.push(dataMap[preLabel + new Date().getFullYear()] || 0);
        }

        document.getElementById('spinner').style.display = 'none';

        myChart.data.labels = labels;
        myChart.data.datasets[0].data = values;
        myChart.update();
        
        updating = false; // Reset the flag to allow further updates

        }
        update();
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
    
   
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.5.0/axios.min.js"></script>
