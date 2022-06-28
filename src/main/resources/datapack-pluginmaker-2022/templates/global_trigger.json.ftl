{
  "dependencies_provided": [
    <#list data.dependencies as dependency>
        {
            "name": "${dependency.getName()}",
            "type": "${dependency.getRawType()}"
        }<#if dependency?has_next>,</#if>
    </#list>
  ],
  "cancelable": ${data.cancelable},
  "has_result": ${data.hasResult},
  <#if data.side != "both">
  "side": "${data.side}",</#if>
  "required_apis": [
    <#list data.requiredAPIs as api>
    "${api}"<#if api?has_next>,</#if>
    </#list>
  ]
}