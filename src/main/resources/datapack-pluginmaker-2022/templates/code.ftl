<#-- @formatter:off -->
<#if var_generator == "forge-1.16.5">
    ${data.getCodeTemplate("forge-1.16.5")}
<#elseif var_generator == "forge-1.18.2">
    ${data.getCodeTemplate("forge-1.18.2")}
<#elseif var_generator == "fabric-1.17.1">
    ${data.getCodeTemplate("fabric-1.17.1")}
</#if>
<#-- @formatter:on -->