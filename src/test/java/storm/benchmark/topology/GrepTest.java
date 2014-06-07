package storm.benchmark.topology;

import backtype.storm.Config;
import backtype.storm.generated.StormTopology;
import backtype.storm.utils.Utils;
import org.testng.annotations.Test;
import storm.benchmark.StormBenchmark;
import storm.benchmark.util.TestUtils;

import static org.fest.assertions.api.Assertions.assertThat;

public class GrepTest {

  @Test
  public void componentParallelismCouldBeSetThroughConfig() {
    StormBenchmark benchmark = new Grep();
    Config config = new Config();
    config.put(Grep.SPOUT_NUM, 3);
    config.put(Grep.FM_NUM, 4);
    config.put(Grep.CM_NUM, 5);
    StormTopology topology = benchmark.getTopology(config);
    assertThat(topology).isNotNull();
    TestUtils.verifyParallelism(Utils.getComponentCommon(topology, Grep.SPOUT_ID), 3);
    TestUtils.verifyParallelism(Utils.getComponentCommon(topology, Grep.FM_ID), 4);
    TestUtils.verifyParallelism(Utils.getComponentCommon(topology, Grep.CM_ID), 5);
  }
}
