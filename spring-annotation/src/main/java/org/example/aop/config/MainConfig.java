package org.example.aop.config;

import org.example.aop.aspect.LogAspects;
import org.example.aop.service.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 1.AOP: 基于动态代理,指在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式
 * 2.编写一个AOP案例的步骤:
 *      2.1.导入aop模块: 引入Spring AOP依赖,即spring-aspects
 *      2.2.定义一个业务逻辑类: (如: MathCalculator) 在业务逻辑运行的时候将日志进行打印(方法之前、方法运行结束、方法出现异常、xxx）
 *      2.3.定义一个日志切面类: (如: LogAspects) 切面类里面的方法需要动态感知MathCalculator.div运行到哪里然后执行
 *          2.3.1.通知方法:
 *              前置通知(@Before):logStart: 在目标方法(div)运行之前运行
 *              后置通知(@After): logEnd: 在目标方法(div)运行结束之后运行(无论方法正常结束还是异常结束)
 *              返回通知(@AfterReturning): logReturn: 在目标方法(div)正常返回之后运行
 *              异常通知(@AfterThrowing): logException: 在目标方法(div)出现异常以后运行
 *              环绕通知(@Around): 动态代理,手动推进目标方法运行(joinPoint.procced())
 *      2.4.给切面类的目标方法标注通知注解,指定何时何地运行
 *      2.5.将切面类和业务逻辑类(目标方法所在类)都加入到容器中
 *      2.6.必须告诉Spring哪个类是切面类(给切面类上加一个注解: @Aspect)
 *      2.7.给配置类中加 @EnableAspectJAutoProxy注解,用于开启基于注解的aop模式
 *  总结:
 *      1.将业务逻辑组件和切面类都加入到容器中,告诉Spring哪个是切面类(@Aspect)
 *      2.在切面类上的每一个通知方法上标注通知注解,告诉Spring何时何地运行(切入点表达式)
 *      3.开启基于注解的aop模式: @EnableAspectJAutoProxy
 * 3.AOP原理: 关注点->看给容器中注册了什么组件,这个组件什么时候工作,这个组件的功能是什么
 *      3.1.@EnableAspectJAutoProxy:
 *          @Import(AspectJAutoProxyRegistrar.class): 给容器中导入AspectJAutoProxyRegistrar
 *          (利用AspectJAutoProxyRegistrar给容器中自定义注册bean)
 *          (注册的组件名internalAutoProxyCreator,组件的类型AnnotationAwareAspectJAutoProxyCreator)
 *      3.2.AnnotationAwareAspectJAutoProxyCreator的继承关系: (从上到下为子到父的关系)
 *          AnnotationAwareAspectJAutoProxyCreator
 *              ->AspectJAwareAdvisorAutoProxyCreator
 *                  ->AbstractAdvisorAutoProxyCreator
 *                      ->AbstractAutoProxyCreator
 *                          implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                      (关注后置处理器(在bean初始化完成前后做事情),自动装配BeanFactory)
 *      3.3.根据以上的继承关系可得AnnotationAwareAspectJAutoProxyCreator类中有两个方法来进行处理:
 *          3.3.1.setBeanFactory()方法的重写与继承关系:(从上到下为父到子的关系)
 *              AbstractAutoProxyCreator.setBeanFactory()
 *                  -> AbstractAdvisorAutoProxyCreator.setBeanFactory() [重写]
 *                      -> AspectJAwareAdvisorAutoProxyCreator.setBeanFactory() [继承]
 *          3.3.2.postProcessBeforeInstantiation()方法的重写与继承关系:(从上到下为父到子的关系)
 *              AbstractAutoProxyCreator.postProcessBeforeInstantiation()/postProcessAfterInitialization()
 *                  -> AspectJAwareAdvisorAutoProxyCreator.postProcessBeforeInstantiation()  [继承]
 * 4.流程:
 *      4.1.传入配置类,创建ioc容器
 *          4.1.1.this()中创建的容器
 *      4.2.注册配置类,再调用refresh()刷新容器
 *          4.2.1.refresh()中的obtainFreshBeanFactory()获取到创建的容器并在后续逻辑中传递着容器对象
 *      4.3.registerBeanPostProcessors(beanFactory): 注册bean的后置处理器来方便拦截bean的创建
 *          4.3.1.先获取ioc容器已经定义了的需要创建对象的所有BeanPostProcessor: beanFactory.getBeanNamesForType(...)
 *          4.3.2.给容器中加别的BeanPostProcessor: beanFactory.addBeanPostProcessor(...)
 *          4.3.3.优先注册实现了PriorityOrdered接口的BeanPostProcessor
 *          4.3.4.再给容器中注册实现了Ordered接口的BeanPostProcessor
 *          4.3.5.注册没实现优先级接口的BeanPostProcessor
 *          4.3.6.注册BeanPostProcessor实例实际是通过beanFactory.getBean(...)来实现,getBean()中若未获取到实例则需要创建
 *              4.3.6.1.创建Bean的实例: createBean(...)->doCreateBean(...)->createBeanInstance(...)->instanceWrapper.getWrappedInstance()
 *              4.3.6.2.给bean的各种属性赋值: populateBean()
 *              4.3.6.3.初始化bean: initializeBean()[重要]
 *                  4.3.6.3.1.invokeAwareMethods(): 处理Aware接口的方法回调
 *                      由于AOP注册的组件实现了BeanFactoryAware,因此会调用bean.setBeanFactory(...),进而回调组件的setBeanFactory(...)并根据3.3.1中继承关系执行相应逻辑
 *                  4.3.6.3.2.applyBeanPostProcessorsBeforeInitialization(): 应用后置处理器的postProcessBeforeInitialization()
 *                  4.3.6.3.3.invokeInitMethods(): 执行自定义的初始化方法
 *                  4.3.6.3.4.applyBeanPostProcessorsAfterInitialization(): 执行后置处理器的postProcessAfterInitialization()
 *          4.3.7.BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功 -> aspectJAdvisorsBuilder
 *   ====================以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程====================
 *          AnnotationAwareAspectJAutoProxyCreator implements SmartInstantiationAwareBeanPostProcessor
 *                                                              ->InstantiationAwareBeanPostProcessor
 *          InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation()
 *          InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation()
 *      4.4.finishBeanFactoryInitialization(beanFactory)->beanFactory.preInstantiateSingletons(): 完成BeanFactory初始化工作,创建剩下的单实例bean
 *          4.4.1.遍历获取容器中所有的beanDefinition,依次通过getBean(...)创建实例对象
 *              getBean(...)->doGetBean(...)->getSingleton(...)->createBean(...)
 *          4.4.2.createBean(...): 创建bean
 *              4.4.2.1.resolveBeforeInstantiation(beanName, mbdToUse): 解析BeforeInstantiation
 *                  希望后置处理器在此能返回一个代理对象,如果能返回代理对象就使用,如果不能就继续
 *                  4.4.2.1.1.applyBeanPostProcessorsBeforeInstantiation(...):
 *                      拿到所有后置处理器,如果是InstantiationAwareBeanPostProcessor类型,就执行该后置处理器的postProcessBeforeInstantiation()
 *                  4.4.2.1.1.applyBeanPostProcessorsAfterInitialization(...):
 *              4.4.2.2.doCreateBean(beanName, mbdToUse, args): 创建实例对象,与流程4.3.6一样
 *              4.4.2.3.注:
 *                  BeanPostProcessor是在Bean对象创建完成初始化前后调用的
 *                  InstantiationAwareBeanPostProcessor是在创建Bean实例之前先尝试用后置处理器返回对象的
 * 5.AnnotationAwareAspectJAutoProxyCreator[InstantiationAwareBeanPostProcessor]的作用:
 *      5.1.每一个bean创建之前,调用postProcessBeforeInstantiation(): (主要关心MathCalculator和LogAspect的创建)
 *          5.1.1.判断当前bean是否在advisedBeans中(保存了所有需要增强bean)
 *          5.1.2.判断当前bean是否是基础类型的Advice、Pointcut、Advisor、AopInfrastructureBean或者是否是切面(@Aspect)
 *          5.1.3.是否需要跳过:
 *              5.1.3.1.获取候选的增强器: (即切面内的通知方法的集合)
 *                  List<Advisor> candidateAdvisors = findCandidateAdvisors();
 *                  Advisor的类型为InstantiationModelAwarePointcutAdvisor
 *              5.1.3.2.判断每一个增强器是否是AspectJPointcutAdvisor类型:
 *                  由于所有的候选的增强器都是InstantiationModelAwarePointcutAdvisor类型,所有都不满足
 *              5.1.3.2.使用父类默认方法判断是否跳过: 父类实现直接返回false
 *      5.2.创建对象后,调用postProcessAfterInitialization():
 *          5.2.1.wrapIfNecessary(...): 如果需要的话,对bean进行一下包装
 *              5.2.1.1.获取当前bean的所有增强器(通知方法): Object[] specificInterceptors = getAdvicesAndAdvisorsForBean(...)
 *                  5.2.1.1.1.找到候选的所有的增强器: (找哪些通知方法是需要切入当前bean方法的)
 *                      List<Advisor> eligibleAdvisors = findAdvisorsThatCanApply(...)
 *                  5.2.1.1.2.获取到能在bean使用的增强器:
 *                      AopUtils.findAdvisorsThatCanApply(...) -> canApply(...)
 *                  5.2.1.1.3.给增强器排序: sortAdvisors(...)
 *              5.2.1.2.保存当前bean在advisedBeans中: advisedBeans.put(...)
 *              5.2.1.3.如果当前bean需要增强,创建当前bean的代理对象: createProxy(...)
 *                  5.2.1.3.1.获取所有增强器(通知方法): buildAdvisors(...)
 *                  5.2.1.3.2.保存到proxyFactory: proxyFactory.addAdvisors(advisors)
 *                  5.2.1.3.3.创建代理对象(由Spring决定):
 *                      JdkDynamicAopProxy(config): jdk动态代理
 *                      ObjenesisCglibAopProxy(config): cglib的动态代理
 *                  5.2.1.3.4.给容器中返回当前组件使用cglib增强了的代理对象
 *                  5.2.1.3.5.以后容器中获取到的就是这个组件的代理对象,执行目标方法的时候,代理对象就会执行通知方法的流程
 *      5.3.目标方法执行: 容器中保存了组件的代理对象(cglib增强后的对象),这个对象里面保存了详细信息(比如增强器、目标对象等)
 *          5.3.1.CglibAopProxy.intercept(): 拦截目标方法的执行
 *          5.3.2.根据ProxyFactory对象(this.advised)获取将要执行的目标方法拦截器链:
 *              List<Object> chain = advised.getInterceptorsAndDynamicInterceptionAdvice(...)->
 *                  advisorChainFactory.getInterceptorsAndDynamicInterceptionAdvice(...)
 *              5.3.2.1.准备保存拦截器的容器:
 *                  容器大小: 5(一个默认的ExposeInvocationInterceptor和4个增强器)
 *              5.3.2.2.遍历所有的增强器,将其转为Interceptor:
 *                  registry.getInterceptors(advisor)
 *              5.3.2.3.将增强器转为List<MethodInterceptor>:
 *                  如果是MethodInterceptor类型,直接加入到集合中
 *                  如果不是,使用适配器AdvisorAdapter将增强器转为MethodInterceptor类型
 *          5.3.3.如果没有拦截器链,直接执行目标方法:
 *              retVal = methodProxy.invoke(target, argsToUse)
 *          5.3.4.如果有拦截器链,将执行的目标对象、目标方法、拦截器链等以构造参数来创建CglibMethodInvocation对象,并执行其proceed()方法:
 *              每一个通知方法又被包装为方法拦截器,利用MethodInterceptor机制
 *          5.3.5.拦截器链的触发过程: CglibMethodInvocation.proceed()
 *              5.3.5.1.如果拦截器链中的拦截器数量为0或拦截器链中拦截器每执行一个当前索引+1至全部执行完为size-1时,执行目标方法:
 *                  invokeJoinpoint() -> AopUtils.invokeJoinpointUsingReflection(...) -> method.invoke(...)
 *              5.3.5.2.链式获取每一个拦截器,拦截器执行invoke方法: (拦截器链机制)
 *                  5.3.5.2.1.获取拦截器链中第1个即索引为0的拦截器,并执行拦截器的invoke(...):
 *                      ExposeInvocationInterceptor.invoke(...)->
 *                          invocation.set(mi): 将拦截器存起来 ->
 *                          mi.proceed(): 再执行proceed()方法去拿第2个拦截器执行
 *                  5.3.5.2.2.获取拦截器链中第2个即索引为1的拦截器,并执行拦截器的invoke(...):
 *                      MethodBeforeAdviceInterceptor.invoke(...)->
 *                          advice.before(...): 执行前置通知方法 ->
 *                          mi.proceed(): 再执行proceed()方法去拿第3个拦截器执行
 *                  5.3.5.2.3.获取拦截器链中第3个即索引为2的拦截器,并执行拦截器的invoke(...):
 *                      AspectJAfterAdvice.invoke(...)->
 *                          try {
 *                              return mi.proceed(); // 再执行proceed()方法去拿第4个拦截器执行
 *                          } finally {
 *                              invokeAdviceMethod(getJoinPointMatch(), null, null); // 执行后置通知方法
 *                          }
 *                  5.3.5.2.4.获取拦截器链中第4个即索引为3的拦截器,并执行拦截器的invoke(...):
 *                      AfterReturningAdviceInterceptor.invoke(...)->
 *                          mi.proceed(): 再执行proceed()方法去拿第5个拦截器执行 ->
 *                          advice.afterReturning(...): 执行返回通知方法
 *                  5.3.5.2.5.获取拦截器链中第5个即索引为4的拦截器,并执行拦截器的invoke(...):
 *                      AspectJAfterThrowingAdvice.invoke(...)->
 *                          try {
 * 			                    return mi.proceed(); // 再执行proceed()方法去执行目标方法
 *                          } catch (Throwable ex) {
 * 			                    if (shouldInvokeOnThrowing(ex)) {
 * 				                    invokeAdviceMethod(getJoinPointMatch(), null, ex); // 执行异常通知方法
 *                              }
 * 			                    throw ex;
 *                          }
 *                  5.3.5.2.6.执行目标方法:
 *                      invokeJoinpoint() -> AopUtils.invokeJoinpointUsingReflection(...) -> method.invoke(...) ->
 *                      执行完目标方法后,按执行链路反向回到上层执行的位置(和递归是同样的道理),执行剩下的逻辑.
 * 6.总结:
 *      6.1.在配置类上使用注解@EnableAspectJAutoProxy来开启AOP功能
 *      6.2.注解@EnableAspectJAutoProxy内使用注解@Import+ImportBeanDefinitionRegistrar给容器中注册一个组件AnnotationAwareAspectJAutoProxyCreator
 *          在组件AspectJAutoProxyRegistrar的registerBeanDefinitions()方法中注册的AnnotationAwareAspectJAutoProxyCreator
 *          AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
 *      6.3.容器创建过程中与AOP相关的主要流程:
 *          6.3.1.registerBeanPostProcessors(): 注册后置处理器,创建AnnotationAwareAspectJAutoProxyCreator实例对象
 *          6.3.2.finishBeanFactoryInitialization(): 初始化剩下的单实例bean
 *              6.3.2.1.创建业务逻辑组件和切面组件
 *              6.3.2.2.AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
 *              6.3.2.3.组件创建完之后,判断组件是否需要增强:
 *                  是: 切面的通知方法，包装成增强器(Advisor);给业务逻辑组件创建一个代理对象(cglib)
 *      6.4.执行目标方法:
 *          6.4.1.代理对象执行目标方法
 *          6.4.2.CglibAopProxy.intercept():
 *              6.4.2.1.得到目标方法的拦截器链(增强器包装成拦截器MethodInterceptor)
 *              6.4.2.2.利用拦截器的链式机制,依次进入每一个拦截器进行执行
 *              6.4.2.3.效果:
 *                  正常执行: 前置通知->目标方法->后置通知->返回通知
 *                  出现异常: 前置通知->目标方法->后置通知->异常通知
 *
 * @author: dengzm
 * @date: 2021-07-21 20:32:10
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfig {
    //业务逻辑类加入容器中
    @Bean
    public MathCalculator calculator(){
        return new MathCalculator();
    }

    //切面类加入到容器中
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}
