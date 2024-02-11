export function formatLocalDateTime(localDateTimeArray) {
    const date = new Date(
        localDateTimeArray[0],
        localDateTimeArray[1] - 1,
        localDateTimeArray[2],
        localDateTimeArray[3],
        localDateTimeArray[4],
        localDateTimeArray[5]
    );

    const formattedDate =`${date.getHours()}:${date.getMinutes()}:${date.getSeconds()} ${date.getDate()}.${date.getMonth() + 1}.${date.getFullYear()}`;

    return formattedDate;
}

export function formatUnixTimestamp(timestamp) {
    const date = new Date(timestamp);

        const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const year = date.getFullYear();


    const formattedDate = `${day}.${month}.${year}`;

    return formattedDate;
}

