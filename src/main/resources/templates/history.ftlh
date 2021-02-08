<!doctype html>
<html lang="en">
<head>
    <title>CI - Build History</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar bg-light">
        <div class="container">
            <span class="navbar-brand">DD2480 Group 7: CI Server</span>
            </div>
        </div>
    </nav>
    <div class="container">
        <div class="row mt-5">
            <div class="mb-4 col-12 col-md-4 col-lg-3">
                <#list buildList as item>
                    <div class="card mb-3">
                        <div class="card-body">
                            <h5 class="card-title">${item.branch}
                                <#if item.status == "success">
                                    <span class="badge bg-success float-end">Passed</span>
                                <#elseif item.status == "failure">
                                    <span class="badge bg-danger float-end">Failed</span>
                                <#else>
                                    <span class="badge bg-warning float-end">Error</span>
                                </#if>
                            </h5>
                            <h6 class="card-subtitle mb-2 text-muted">${item.repo}</h6>
                            <p class="card-text">
                                <small class="text-muted">Finished: ${item.timeString}</small>
                                <a href="/?build=${item.id}" class="stretched-link"></a>
                            </p>
                        </div>
                    </div>
                </#list>

            </div>
            <div class="mb-4 col-12 col-md-8 col-lg-9">
                <#if build??>
                    <h4>
                        <#if build.status == "success">
                            <span class="badge bg-success">Passed</span>
                        <#elseif build.status == "failure">
                            <span class="badge bg-danger">Failed</span>
                        <#else>
                            <span class="badge bg-warning">Error</span>
                        </#if>
                        ${build.branch}
                        <span class="text-muted">${build.repo}</span>
                    </h4>
                    <p>
                        <span class="text-muted">Commit:</span> ${build.sha}<br>
                        <span class="text-muted">Build finished:</span> ${build.timeString}
                    </p>
                    <h5>Build Log</h5>
                    <pre class="bg-light" style="white-space: pre-wrap; word-break: break-all;">${build.log}</pre>
                <#else>
                    <p class="text-muted">No build selected</p>
                </#if>
            </div>
        </div>
    </div>

    <script src="/static/js/bootstrap.bundle.min.js"></script>
</body>

</html>
