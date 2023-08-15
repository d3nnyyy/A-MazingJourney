function getStats(percentage) {
    if (percentage > 100) {
        return `Your path was ${Math.round((percentage - 100) * 100) / 100}% longer than the shortest path.`
    }
    else {
        return 'Your path has the same length as the shortest path!'
    }
}

export default getStats