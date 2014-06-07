package storm.benchmark.topology;

import backtype.storm.Config;
import backtype.storm.generated.StormTopology;
import backtype.storm.utils.Utils;
import org.testng.annotations.Test;
import storm.benchmark.StormBenchmark;
import storm.benchmark.util.TestUtils;

import static org.fest.assertions.api.Assertions.assertThat;

public class DataCleanTest {

  @Test
  public void componentParallelismCouldBeSetThroughConfig() {
    StormBenchmark benchmark = new DataClean();
    Config config = new Config();
    config.put(DataClean.SPOUT_NUM, 3);
    config.put(DataClean.VIEW_NUM, 4);
    config.put(DataClean.FILTER_NUM, 5);

    StormTopology topology = benchmark.getTopology(config);
    assertThat(topology).isNotNull();
    TestUtils.verifyParallelism(Utils.getComponentCommon(topology, DataClean.SPOUT_ID), 3);
    TestUtils.verifyParallelism(Utils.getComponentCommon(topology, DataClean.VIEW_ID), 4);
    TestUtils.verifyParallelism(Utils.getComponentCommon(topology, DataClean.FILTER_ID), 5);
  }
}
