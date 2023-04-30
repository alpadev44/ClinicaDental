
    $(document).ready(function() {
        $('#logoutBtn').click(function() {
            $.post('/logout', function() {
                window.location.replace('/login');
            });
        });
    });
