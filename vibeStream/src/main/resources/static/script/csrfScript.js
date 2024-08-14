

document.addEventListener("DOMContentLoaded", () => {
    // Get CSRF token from meta tags
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    // Lambda function to handle like button click
    window.handleLikeClick = (songId, element) => {
        console.log("Liked song ID:", songId);

        // Change the image to the "liked" state
        element.src = "https://github.com/Jayamalini20/mp3Files/blob/main/like_solid.png?raw=true";

        // Send AJAX request with CSRF token
        fetch('/like/' + songId, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify({ songId: songId })
        })
        .then(response => {
            if (response.ok) {
                console.log("Song liked successfully");
            } else {
                console.error("Failed to like song");
            }
        })
        .catch(error => console.error("Error:", error));
    };
});
