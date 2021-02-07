<html>
    <head>
        <title>CI - Build History</title>
    </head>
    <body>
        <#list buildList as build>
            <a href="/?build=${build.id}">${build.repo}, ${build.branch}, ${build.time}</a><br>
        </#list>

        <br><br>

        <#if buildSelected == true>
            <h1>${repo}: ${branch} (#${id})</h1>
            <pre>${log}</pre>
        <#else>
            <p>No build selected</p>
        </#if>
    </body>
</html>
