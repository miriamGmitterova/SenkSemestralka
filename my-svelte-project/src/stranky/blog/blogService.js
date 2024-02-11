import { onMount } from 'svelte';
import { writable } from 'svelte/store';
import { formatLocalDateTime } from '../../utilities/DateTimeFormatter.js';
import {navigate} from "svelte-routing";


const apiData = writable([]);

const storedData = JSON.parse(localStorage.getItem('aktualnyPouzivatel'));
console.log(storedData);

onMount(async () => {
    try {
        const response = await fetch('http://localhost:8080/prispevok/zoznam');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();

        apiData.set(data);
    } catch (error) {
        console.error('Error fetching data:', error);
    }
});



onMount(async () => {
    try {
        const response = await fetch('http://localhost:8080/prispevok/zoznam');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();

        apiData.set(data);
    } catch (error) {
        console.error('Error fetching data:', error);
    }
});

function redirectToCreatePost() {
    window.location.href = '/blog/vytvor-prispevok';
}

function redirectToPost(id) {
    navigate(`/blog/${id}`);
}