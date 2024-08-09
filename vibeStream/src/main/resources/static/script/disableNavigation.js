(function () {
    history.pushState(null, null, document.title);
    window.addEventListener('popstate', function (event) {
        history.pushState(null, null, document.title);
    });
})();
