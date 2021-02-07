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
                <#list buildList as build>
                    <div class="card mb-3">
                        <div class="card-body">
                            <h5 class="card-title">${build.branch}
                                <#if build.status == "passed">
                                    <span class="badge bg-success float-end">Passed</span>
                                <#elseif build.status == "failed">
                                    <span class="badge bg-danger float-end">Failed</span>
                                <#else>
                                    <span class="badge bg-warning float-end">Error</span>
                                </#if>
                            </h5>
                            <h6 class="card-subtitle mb-2 text-muted">${build.repo}</h6>
                            <p class="card-text">
                                <small class="text-muted">Finished: ${build.time}</small>
                                <a href="/?build=${build.id}" class="stretched-link"></a>
                            </p>
                        </div>
                    </div>
                </#list>

            </div>
            <div class="mb-4 col-12 col-md-8 col-lg-9">
                <#if buildSelected == true>
                    <h4>
                        <#if status == "passed">
                            <span class="badge bg-success">Passed</span>
                        <#elseif status == "failed">
                            <span class="badge bg-danger">Failed</span>
                        <#else>
                            <span class="badge bg-warning">Error</span>
                        </#if>
                        ${branch}
                        <span class="text-muted">${repo}</span>
                    </h4>
                    <p>
                        <span class="text-muted">Commit:</span> ${commit}<br>
                        <span class="text-muted">Build finished:</span> ${time}
                    </p>
                    <h5>Build Log</h5>
                    <pre class="bg-light" style="white-space: pre-wrap; word-break: break-all;">${log}</pre>
                <#else>
                    <p class="text-muted">No build selected</p>
                </#if>
            </div>
        </div>
    </div>

    <script src="/static/js/bootstrap.bundle.min.js"></script>
</body>

</html>
